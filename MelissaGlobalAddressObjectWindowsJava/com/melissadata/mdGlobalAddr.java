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

public final static class AccessType {
	public final static mdGlobalAddr.AccessType Local=new mdGlobalAddr.AccessType("Local",0);
	public final static mdGlobalAddr.AccessType Remote=new mdGlobalAddr.AccessType("Remote",1);

	private final String enumName;
	private final int enumValue;
	private static AccessType[] enumValues={Local,Remote};

	private AccessType(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AccessType toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AccessType.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class DiacriticsMode {
	public final static mdGlobalAddr.DiacriticsMode Auto=new mdGlobalAddr.DiacriticsMode("Auto",0);
	public final static mdGlobalAddr.DiacriticsMode On=new mdGlobalAddr.DiacriticsMode("On",1);
	public final static mdGlobalAddr.DiacriticsMode Off=new mdGlobalAddr.DiacriticsMode("Off",2);

	private final String enumName;
	private final int enumValue;
	private static DiacriticsMode[] enumValues={Auto,On,Off};

	private DiacriticsMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static DiacriticsMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+DiacriticsMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class StandardizeMode {
	public final static mdGlobalAddr.StandardizeMode ShortFormat=new mdGlobalAddr.StandardizeMode("ShortFormat",0);
	public final static mdGlobalAddr.StandardizeMode LongFormat=new mdGlobalAddr.StandardizeMode("LongFormat",1);
	public final static mdGlobalAddr.StandardizeMode AutoFormat=new mdGlobalAddr.StandardizeMode("AutoFormat",2);

	private final String enumName;
	private final int enumValue;
	private static StandardizeMode[] enumValues={ShortFormat,LongFormat,AutoFormat};

	private StandardizeMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static StandardizeMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+StandardizeMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class SuiteParseMode {
	public final static mdGlobalAddr.SuiteParseMode ParseSuite=new mdGlobalAddr.SuiteParseMode("ParseSuite",0);
	public final static mdGlobalAddr.SuiteParseMode CombineSuite=new mdGlobalAddr.SuiteParseMode("CombineSuite",1);

	private final String enumName;
	private final int enumValue;
	private static SuiteParseMode[] enumValues={ParseSuite,CombineSuite};

	private SuiteParseMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static SuiteParseMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+SuiteParseMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class AliasPreserveMode {
	public final static mdGlobalAddr.AliasPreserveMode ConvertAlias=new mdGlobalAddr.AliasPreserveMode("ConvertAlias",0);
	public final static mdGlobalAddr.AliasPreserveMode PreserveAlias=new mdGlobalAddr.AliasPreserveMode("PreserveAlias",1);

	private final String enumName;
	private final int enumValue;
	private static AliasPreserveMode[] enumValues={ConvertAlias,PreserveAlias};

	private AliasPreserveMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AliasPreserveMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AliasPreserveMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class AutoCompletionMode {
	public final static mdGlobalAddr.AutoCompletionMode AutoCompleteSingleSuite=new mdGlobalAddr.AutoCompletionMode("AutoCompleteSingleSuite",0);
	public final static mdGlobalAddr.AutoCompletionMode AutoCompleteRangedSuite=new mdGlobalAddr.AutoCompletionMode("AutoCompleteRangedSuite",1);
	public final static mdGlobalAddr.AutoCompletionMode AutoCompletePlaceHolderSuite=new mdGlobalAddr.AutoCompletionMode("AutoCompletePlaceHolderSuite",2);
	public final static mdGlobalAddr.AutoCompletionMode AutoCompleteNoSuite=new mdGlobalAddr.AutoCompletionMode("AutoCompleteNoSuite",3);

	private final String enumName;
	private final int enumValue;
	private static AutoCompletionMode[] enumValues={AutoCompleteSingleSuite,AutoCompleteRangedSuite,AutoCompletePlaceHolderSuite,AutoCompleteNoSuite};

	private AutoCompletionMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AutoCompletionMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AutoCompletionMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class ResultCdDescOpt {
	public final static mdGlobalAddr.ResultCdDescOpt ResultCodeDescriptionLong=new mdGlobalAddr.ResultCdDescOpt("ResultCodeDescriptionLong",0);
	public final static mdGlobalAddr.ResultCdDescOpt ResultCodeDescriptionShort=new mdGlobalAddr.ResultCdDescOpt("ResultCodeDescriptionShort",1);

	private final String enumName;
	private final int enumValue;
	private static ResultCdDescOpt[] enumValues={ResultCodeDescriptionLong,ResultCodeDescriptionShort};

	private ResultCdDescOpt(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static ResultCdDescOpt toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+ResultCdDescOpt.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class MailboxLookupMode {
	public final static mdGlobalAddr.MailboxLookupMode MailboxNone=new mdGlobalAddr.MailboxLookupMode("MailboxNone",0);
	public final static mdGlobalAddr.MailboxLookupMode MailboxExpress=new mdGlobalAddr.MailboxLookupMode("MailboxExpress",1);
	public final static mdGlobalAddr.MailboxLookupMode MailboxPremium=new mdGlobalAddr.MailboxLookupMode("MailboxPremium",2);

	private final String enumName;
	private final int enumValue;
	private static MailboxLookupMode[] enumValues={MailboxNone,MailboxExpress,MailboxPremium};

	private MailboxLookupMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static MailboxLookupMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+MailboxLookupMode.class+" with value "+val+".");
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
