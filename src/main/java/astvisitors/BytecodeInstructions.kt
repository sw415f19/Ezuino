package astvisitors

enum class BytecodeInstructions (val bytecodeOp: Int){
	aaload(0x32),
	aastore(0x53),
	aconst_null(0x01),
	aload(0x19),
	aload_0(0x2a),
	aload_1(0x2b),
	aload_2(0x2c),
	aload_3(0x2d),
	anewarray(0xbd),
	areturn(0xb0),
	arraylength(0xbe),
	astore(0x3a),
	
}