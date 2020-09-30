package ch.makery.address.model;

public class TestSettings {

	private int reps;
	private int difficulty;
	private int nBack;
	private int restT;
	private int conT;
	private int expT;
	private int conTimeout;

	private double sigDuration;

	public enum ScanSig{
       MOUSE, KEYBOARD, TIMED

    }

	private ScanSig scanSig;

	private boolean isStrict;
	private boolean isRest;
	private boolean isCon;
	private boolean isExp;
	private boolean isSound;
	private boolean isClick;
	private boolean isTimeout;
	private boolean isPopUp;
	private boolean isStaticFeedback;

	private char scanKey;
	private char leftKey;
	private char rightKey;
	private char confKey;

	private String correct;
	private String nCorrect;
	private String record;
	private String nRecord;
	private String ID;
	private String saveDir;
	private String nameTop;
	private String nameBottom;




	//constructor class, requires arrays with specific orders
	public TestSettings(int[] intVar, boolean[] booVar, char[] charVar, String[] stringVar, ScanSig scanSig, double sigDur){

	    //HAD TO ADD THESE IF ELSE STATEMENTS BECAUSE OF ERROR IF NOT CHANGED BY PARTICIPANT
	    if(intVar[0] == 0){
	        this.reps = 1;
	    }
	    else{
	        this.reps = intVar[0];
	    }

	    this.difficulty = intVar[1];
		this.nBack = intVar[2];
		this.restT = intVar[3];
		this.conT = intVar[4];
		this.expT = intVar[5];
		this.conTimeout = intVar[6];

		this.isStrict = booVar[0];
		this.isRest = booVar[1];
		this.isCon = booVar[2];
		this.isExp = booVar[3];
		this.isSound = booVar[4];
		this.isClick = booVar[5];
		this.isTimeout = booVar[6];
		this.isPopUp = booVar[7];
		this.isStaticFeedback = booVar[8];

		this.scanKey = charVar[0];
		this.leftKey = charVar[1];
		this.rightKey = charVar[2];
		this.confKey = charVar[3];

		this.correct = stringVar[0];
		this.nCorrect = stringVar[1];
		this.record = stringVar[2];
		this.nRecord = stringVar[3];
		this.ID = stringVar[4];
		this.saveDir = stringVar[5];
		this.nameBottom = stringVar[6];
		this.nameTop = stringVar[7];
		this.scanSig = scanSig;

		this.sigDuration = sigDur;


	}


	//int properties "get" functions
	public int getNBack(){
		return this.nBack;
	}

	public int getReps(){
		return this.reps;
	}

	public int getDiff(){
		return this.difficulty;
	}

	public int getRestT(){
		return this.restT;
	}

	public int getConT(){
		return this.conT;
	}

	public int getExpT(){
		return this.expT;
	}

	public int getConTimeout(){
	    return this.conTimeout;
	}

	public double getSigDuration(){
	    return this.sigDuration;
	}


	//boolean properties "get" functions
	public boolean getIsStrict(){
		return this.isStrict;
	}

	public boolean getIsRest(){
		return this.isRest;
	}

	public boolean getIsCon(){
		return this.isCon;
	}

	public boolean getIsExp(){
		return this.isExp;
	}

	public boolean getIsSound(){
		return this.isSound;
	}

	public boolean getIsClick(){
	    return this.isClick;
	}

	public boolean getIsTimeout(){
	    return this.isTimeout;
	}

	public boolean getIsPopUp(){
	    return this.isPopUp;
	}

	public boolean getIsStaticFeedback(){
	    return this.isStaticFeedback;
	}


	//String properties "get" functions
	public char getScanKey(){
		return this.scanKey;
	}

	public char getLeftKey(){
		return this.leftKey;
	}

	public char getRightKey(){
		return this.rightKey;
	}

	public char getConfKey(){
		return this.confKey;
	}

	public String getCorrect(){
		return this.correct;
	}

	public String getNCorrect(){
		return this.nCorrect;
	}

	public String getRecord(){
		return this.record;
	}

	public String getNRecord(){
		return this.nRecord;
	}

	public String getID(){
		return this.ID;
	}

	public String getSaveDir(){
		return this.saveDir;
	}

	public String getNameBottom(){
	    return this.nameBottom;
	}

	public String getNameTop(){
	    return this.nameTop;
	}

	public ScanSig getScanSig(){
        return this.scanSig;
    }

}
