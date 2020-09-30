package ch.makery.address.model;

public class TestSettings {

	private int reps;
	private int difficulty;
	private int nBack;
	private int restT;
	private int conT;
	private int expT;


	public enum ScanSig{
       MOUSE, KEYBOARD, TIMED

    }

	private ScanSig scanSig;

	private boolean isStrict;
	private boolean isRest;
	private boolean isCon;
	private boolean isExp;
	private boolean isSound;

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




	//constructor class, requires arrays with specific orders
	public TestSettings(int[] intVar, boolean[] booVar, char[] charVar, String[] stringVar, ScanSig scanSig){

	    //HAD TO ADD THESE IF ELSE STATEMENTS BECAUSE OF ERROR IF NOT CHANGED BY PARTICIPANT
	    if(intVar[0] == 0){
	        this.reps = 1;
	    }
	    else{
	        this.reps = intVar[0];
	    }

	    this.difficulty = intVar[1];
		this.nBack = intVar[2];

		if(intVar[3] == 0){
		    this.restT = 10;
		}
		else{
		    this.restT = intVar[3];
		}
		if(intVar[4] == 0){
		    this.conT = 10;
		}
		else{
		    this.conT = intVar[4];
		}
		if(intVar[5] == 0){
		    this.expT = 10;
		}
		else{
		    this.expT = intVar[5];
		}

		this.isStrict = booVar[0];
		this.isRest = booVar[1];
		this.isCon = booVar[2];
		this.isExp = booVar[3];
		this.isSound = booVar[4];

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
		this.scanSig = scanSig;


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

	public ScanSig getScanSig(){
        return this.scanSig;
    }

}
