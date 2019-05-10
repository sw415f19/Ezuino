package astvisitors;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class JasminCodeGeneratorVisitorTest {

	@Test
	public void predefinedProgramsTest() {
		File ezuinoSourceFileDir = new File("./src/test/ezuino-files/");
		List<File> ezuinoSourceFiles = Arrays.asList(ezuinoSourceFileDir.listFiles()).stream()
				.filter(x -> x.getName().matches(".*.ezuino")).collect(Collectors.toList()); // only add .ezuino files
		List<File> jasminFiles = new ArrayList<File>();
		String[] runParams = new String[ezuinoSourceFiles.size() + 2];
		runParams[0] = "-d";
		runParams[1] = ezuinoSourceFileDir.getPath() + "/";
		int i = 2;

		for (File file : ezuinoSourceFiles) {
			File outputFile = new File(file.getPath().replace(".ezuino", ".j"));
			try {
				compileToJasmin(file, outputFile);
			} catch (FileNotFoundException e) {
				System.out.println("Could not write to file: " + outputFile.getName());
				fail();
			}
			jasminFiles.add(outputFile);
			runParams[i] = outputFile.getPath();
			i++;
		}
		jasminFiles.forEach(file -> file.deleteOnExit()); // comment this line out to inspect .j files
		jasmin.Main.main(runParams);
		List<File> classFiles = Arrays.asList(ezuinoSourceFileDir.listFiles()).stream()
				.filter(x -> x.getName().matches(".*.class")).collect(Collectors.toList()); // only add .class files
		classFiles.forEach(file -> file.deleteOnExit()); // comment this line out to inspect .class files
		for (File classFile : classFiles) {
			Process proc;
			try {
				proc = Runtime.getRuntime().exec("java -classpath " + ezuinoSourceFileDir.getPath() + " "
						+ classFile.getName().replace(".class", ""));
				InputStream pIn = proc.getInputStream();
				InputStream pErr = proc.getErrorStream();
				assertEquals(-1, pErr.read());

			} catch (IOException e) {

			}

		}
	}

	private void compileToJasmin(File inputFile, File outputFile) throws FileNotFoundException {
		ParseTree parseTree = parseANTLR(inputFile);
		AstNode startNode = buildAndDecorateAST(parseTree);
		generateJasminToOutput(startNode, outputFile);
	}

	private ParseTree parseANTLR(File inputFile) {
		CharStream cs = null;
		try {
			cs = CharStreams.fromFileName(inputFile.getPath());
		} catch (IOException e) {
			System.out.println("Could not find file: " + inputFile.getName());
		}
		EzuinoLexer lLexer = new EzuinoLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream(lLexer);
		EzuinoParser parser = new EzuinoParser(tokens);
		return parser.start();
	}

	private AstNode buildAndDecorateAST(ParseTree parseTree) {
		BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
		ErrorHandler errorhandler = new ErrorHandler();
		AstNode astNode = parseTree.accept(buildAstVisitor);
		SymbolTableVisitor symbolTableFillingVisitor = new SymbolTableVisitor(false, errorhandler);
		astNode.accept(symbolTableFillingVisitor);
		Typechecker tc = new Typechecker(errorhandler);
		astNode.accept(tc);
		return astNode;
	}

	private void generateJasminToOutput(AstNode startNode, File outputFile) throws FileNotFoundException {
		PrintStream ps = new PrintStream(outputFile);
		JasminCodeGeneratorVisitor jscgv = new JasminCodeGeneratorVisitor(ps, outputFile.getName().replace(".j", ""));
		startNode.accept(jscgv);
	}
}
