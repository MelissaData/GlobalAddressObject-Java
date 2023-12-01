package com.melissadata;

public class mdGlobalAddrJNI {
	static {
		try {
			System.loadLibrary("mdGlobalAddrJavaWrapper");
		} catch (UnsatisfiedLinkError ule) {
			System.out.println(ule);
			System.out.println("java.library.path="+System.getProperty("java.library.path"));
		}
	}

	public final static native long mdGlobalAddrCreate();
	public final static native void mdGlobalAddrDestroy(long I);
	public final static native boolean SetLicenseString(long I,String p1);
	public final static native void SetPathToGlobalAddrFiles(long I,String p1);
	public final static native int InitializeDataFiles(long I);
	public final static native void ClearProperties(long I);
	public final static native boolean SetInputParameter(long I,String pszParamName,String pszParamValue);
	public final static native int VerifyAddress(long I);
	public final static native String GetOutputParameter(long I,String pszParamName);
	public final static native String TransliterateText(long I,String pszInput,String pszInputScript,String pszOutputScript);
	public final static native String GetCurrentAtomSet(long I);
	public final static native String InputsAsAtomSet(long I);
	public final static native String RightFieldResultsAsAtomSet(long I,String pszAtomSet);
	public final static native String TokenizerResultsAsAtomSet(long I,String pszAtomSet);
	public final static native String InputMapperResultsAsAtomSet(long I,String pszAtomSetArray);
	public final static native String MatchEngineResultsAsAtomSet(long I,String pszAtomSetArray);
	public final static native String OutputMappingResultsAsAtomSet(long I,String pszAtomSet);
	public final static native void SetOutputsFromAtomSet(long I,String pszAtomSet);
}
