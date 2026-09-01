package com.melissadata;

public class mdGlobalAddr {
	private long I;
	protected boolean ownMemory;

	protected static long getI(mdGlobalAddr obj) {
		return (obj==null ? 0 : obj.I);
	}

	protected void finalize() {
		delete();
	}

	public final static class ProgramStatus {
		public final static mdGlobalAddr.ProgramStatus ErrorNone=new mdGlobalAddr.ProgramStatus("ErrorNone",0);
		public final static mdGlobalAddr.ProgramStatus ErrorOther=new mdGlobalAddr.ProgramStatus("ErrorOther",1);
		public final static mdGlobalAddr.ProgramStatus ErrorOutOfMemory=new mdGlobalAddr.ProgramStatus("ErrorOutOfMemory",2);
		public final static mdGlobalAddr.ProgramStatus ErrorRequiredFileNotFound=new mdGlobalAddr.ProgramStatus("ErrorRequiredFileNotFound",3);
		public final static mdGlobalAddr.ProgramStatus ErrorFoundOldFile=new mdGlobalAddr.ProgramStatus("ErrorFoundOldFile",4);
		public final static mdGlobalAddr.ProgramStatus ErrorDatabaseExpired=new mdGlobalAddr.ProgramStatus("ErrorDatabaseExpired",5);
		public final static mdGlobalAddr.ProgramStatus ErrorLicenseExpired=new mdGlobalAddr.ProgramStatus("ErrorLicenseExpired",6);

		private final String enumName;
		private final int enumValue;
		private static ProgramStatus[] enumValues={ErrorNone,ErrorOther,ErrorOutOfMemory,ErrorRequiredFileNotFound,ErrorFoundOldFile,ErrorDatabaseExpired,ErrorLicenseExpired};

		private ProgramStatus(String name,int val) {
			enumName=name;
			enumValue=val;
		}

		public static ProgramStatus toEnum(int val) {
			for (int i=0;i<enumValues.length;i++)
				if (enumValues[i].enumValue==val)
					return enumValues[i];
			throw new IllegalArgumentException("No enum "+ProgramStatus.class+" with value "+val+".");
		}

		public String toString() {
			return enumName;
		}

		public int toValue() {
			return enumValue;
		}
	}

	protected mdGlobalAddr(long i,boolean own) {
		ownMemory=own;
		I=i;
	}

	public mdGlobalAddr() {
		this(mdGlobalAddrJNI.mdGlobalAddrCreate(),true);
	}

	public synchronized void delete() {
		if (I!=0) {
			if (ownMemory) {
				ownMemory=false;
				mdGlobalAddrJNI.mdGlobalAddrDestroy(I);
			}
			I=0;
		}
	}

	public boolean SetLicenseString(String p1) {
		return mdGlobalAddrJNI.SetLicenseString(I,p1);
	}

	public void SetPathToGlobalAddrFiles(String p1) {
		mdGlobalAddrJNI.SetPathToGlobalAddrFiles(I,p1);
	}

	public ProgramStatus InitializeDataFiles() {
		return ProgramStatus.toEnum(mdGlobalAddrJNI.InitializeDataFiles(I));
	}

	public void ClearProperties() {
		mdGlobalAddrJNI.ClearProperties(I);
	}

	public boolean SetInputParameter(String pszParamName, String pszParamValue) {
		return mdGlobalAddrJNI.SetInputParameter(I,pszParamName,pszParamValue);
	}

	public int VerifyAddress() {
		return mdGlobalAddrJNI.VerifyAddress(I);
	}

	public String GetOutputParameter(String pszParamName) {
		return mdGlobalAddrJNI.GetOutputParameter(I,pszParamName);
	}

	public String TransliterateText(String pszInput, String pszInputScript, String pszOutputScript) {
		return mdGlobalAddrJNI.TransliterateText(I,pszInput,pszInputScript,pszOutputScript);
	}

	public String GetCurrentAtomSet() {
		return mdGlobalAddrJNI.GetCurrentAtomSet(I);
	}

	public String InputsAsAtomSet() {
		return mdGlobalAddrJNI.InputsAsAtomSet(I);
	}

	public String RightFieldResultsAsAtomSet(String pszAtomSet) {
		return mdGlobalAddrJNI.RightFieldResultsAsAtomSet(I,pszAtomSet);
	}

	public String TokenizerResultsAsAtomSet(String pszAtomSet) {
		return mdGlobalAddrJNI.TokenizerResultsAsAtomSet(I,pszAtomSet);
	}

	public String InputMapperResultsAsAtomSet(String pszAtomSetArray) {
		return mdGlobalAddrJNI.InputMapperResultsAsAtomSet(I,pszAtomSetArray);
	}

	public String MatchEngineResultsAsAtomSet(String pszAtomSetArray) {
		return mdGlobalAddrJNI.MatchEngineResultsAsAtomSet(I,pszAtomSetArray);
	}

	public String OutputMappingResultsAsAtomSet(String pszAtomSet) {
		return mdGlobalAddrJNI.OutputMappingResultsAsAtomSet(I,pszAtomSet);
	}

	public void SetOutputsFromAtomSet(String pszAtomSet) {
		mdGlobalAddrJNI.SetOutputsFromAtomSet(I,pszAtomSet);
	}

}
