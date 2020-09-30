package ch.makery.address.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestQuestion {

    private int answer;
    private String question;


    //Constructor, creates the question based on the difficulty level entered;
    public TestQuestion(int difficulty, int uLimit) {

       switch(difficulty){


       //difficulty level of 1
       case 1:
           //randomly chooses 1 of Level 1 - 3
           int choice = ThreadLocalRandom.current().nextInt(3);

           switch(choice){

           case 0:
               questionLevel1(uLimit);
               break;
           case 1:
               questionLevel2(uLimit);
               break;
           case 2:
               questionLevel3(uLimit);
           }

           break;


       //difficulty level of 2
       case 2:

           //randomly chooses 1 of Level 1 - 4
           int choice2 = ThreadLocalRandom.current().nextInt(4);

           switch(choice2){

           case 0:
               questionLevel1(uLimit);
               break;
           case 1:
               questionLevel2(uLimit);
               break;
           case 2:
               questionLevel3(uLimit);
               break;
           case 3:
               questionLevel4(uLimit);
               break;
           }

           break;


       //difficulty level of 3
       case 3:

           //randomly chooses 1 of Level 1 - 5
           int choice3 = ThreadLocalRandom.current().nextInt(5);

           switch(choice3){

           case 0:
               questionLevel1(uLimit);
               break;
           case 1:
               questionLevel2(uLimit);
               break;
           case 2:
               questionLevel3(uLimit);
               break;
           case 3:
               questionLevel4(uLimit);
               break;
           case 4:
               questionLevel5(uLimit);
               break;
           }

           break;

       //difficulty level of 4
       case 4:

           //randomly chooses 1 of Level 1 - 6
           int choice4 = ThreadLocalRandom.current().nextInt(6);

           switch(choice4){

           case 0:
               questionLevel1(uLimit);
               break;
           case 1:
               questionLevel2(uLimit);
               break;
           case 2:
               questionLevel3(uLimit);
               break;
           case 3:
               questionLevel4(uLimit);
               break;
           case 4:
               questionLevel5(uLimit);
               break;
           case 5:
               questionLevel6(uLimit);
           }

           break;


       //difficulty level of 5
       case 5:

           //randomly chooses 1 of Level 2 - 6
           int choice5 = ThreadLocalRandom.current().nextInt(5);

           switch(choice5){

           case 0:
               questionLevel2(uLimit);
               break;
           case 1:
               questionLevel3(uLimit);
               break;
           case 2:
               questionLevel4(uLimit);
               break;
           case 3:
               questionLevel5(uLimit);
               break;
           case 4:
               questionLevel6(uLimit);
               break;
           }
           break;


       //difficulty level of 6
       case 6:

           //randomly chooses 1 of Level 3 - 6
           int choice6 = ThreadLocalRandom.current().nextInt(4);

           switch(choice6){

           case 0:
               questionLevel3(uLimit);
               break;
           case 1:
               questionLevel4(uLimit);
               break;
           case 2:
               questionLevel5(uLimit);
               break;
           case 3:
               questionLevel6(uLimit);
               break;
           }

           break;
       }

    }



    private void questionLevel1(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(2);

        if(choice == 0){
            List<Integer> input = addQuestion();
            this.answer = input.get(0) + input.get(1);
            this.question = input.get(0).toString() + " + " + input.get(1).toString() + " = ?";
        }
        else{
            List<Integer> input = subQuestion(uLimit);
            this.answer = input.get(0) - input.get(1);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " = ?";
        }

    }


    private void questionLevel2(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(4);

        if(choice == 0){

            List<Integer> input = addSubQuestion(uLimit);
            this.answer = input.get(0) + input.get(1) - input.get(2);
            this.question = input.get(0).toString() + " + " + input.get(1).toString() + " - " + input.get(2) + " = ?";

        }

        else if (choice == 1){

            List<Integer> input = subSubQuestion(uLimit);
            this.answer = input.get(0) - input.get(1) - input.get(2);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " - " + input.get(2).toString() + " = ?";
        }

        else if(choice == 2){

            List<Integer> input = subAddQuestion(uLimit);
            this.answer = input.get(0) - input.get(1) + input.get(2);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " + " + input.get(2).toString() + " = ?";


        }
        else{

            List<Integer> input = mulQuestion();
            this.answer = input.get(0) * input.get(1);
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + " = ?";

        }

    }


    private void questionLevel3(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(5);

        if(choice == 0){

            List<Integer> input = mulAddQuestion();
            this.answer = input.get(0) * input.get(1) + input.get(2);
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + " + " + input.get(2).toString() + " = ?";

        }

        else if (choice == 1){

            List<Integer> input = addMulQuestion();
            this.answer = input.get(0) + input.get(1) * input.get(2);
            this.question = input.get(0).toString() + " + " + input.get(1).toString() + " x " + input.get(2).toString() + " = ?";
        }

        else if(choice == 2){

            List<Integer> input = mulSubQuestion(uLimit);
            this.answer = input.get(0) * input.get(1) - input.get(2);
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + " - " + input.get(2).toString() + " = ?";


        }
        else if(choice == 3){

            List<Integer> input = subMulQuestion(uLimit);
            this.answer = input.get(0) - input.get(1) * input.get(2);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " x " + input.get(2).toString() + " = ?";

        }
        else{

            List<Integer> input = divQuestion(uLimit);
            this.answer = input.get(0) / input.get(1);
            this.question = input.get(0).toString() + " / " + input.get(1).toString() + " = ?";
        }

    }


    private void questionLevel4(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(5);

        if(choice == 0){

            List<Integer> input = fracAddSub(uLimit);
            this.answer = (input.get(0) / input.get(1)) + input.get(2) - input.get(3);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " + " + input.get(2).toString() + " - " + input.get(3).toString() + " = ?";

        }

        else if (choice == 1){

            List<Integer> input = fracSubAdd(uLimit);
            this.answer = (input.get(0) / input.get(1)) - input.get(2) + input.get(3);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + " + " + input.get(3).toString() + " = ?";
        }

        else if(choice == 2){

            List<Integer> input = fracSubSub(uLimit);
            this.answer = (input.get(0) / input.get(1)) - input.get(2) - input.get(3);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + " - " + input.get(3).toString() + " = ?";

        }
        else if(choice == 3){

            List<Integer> input = fracSub(uLimit);
            this.answer = (input.get(0) / input.get(1)) - input.get(2);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + " = ?";
        }
        else{

            List<Integer> input = fracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " = ?";
        }

    }


    private void questionLevel5(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(5);

        if(choice == 0){

            List<Integer> input = fracMulFracAdd(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) + input.get(4);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " + " + input.get(4).toString() + " = ?";
        }
        else if(choice == 1){

            List<Integer> input = fracMulFracSub(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) - input.get(4);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " - " + input.get(4).toString() + " = ?";
        }
        else if(choice == 2){

            List<Integer> input = fracAddSubFrac(uLimit);
            this.answer = input.get(0) / input.get(1) + input.get(2) - input.get(3) / input.get(4);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " + " + input.get(2).toString() + " - " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";
        }
        else if(choice == 3){

            List<Integer> input = fracSubAddFrac(uLimit);
            this.answer = input.get(0) / input.get(1) - input.get(2) + input.get(3) / input.get(4);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + " + " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";

        }
        else{

            List<Integer> input = mulFracMulFrac(uLimit);
            this.answer = (input.get(0) * input.get(1) * input.get(3)) / (input.get(2) * input.get(4));
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + "/" + input.get(2).toString() + " x " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";
        }
    }




    private void questionLevel6(int uLimit){

        int choice = ThreadLocalRandom.current().nextInt(5);

        if(choice == 0){

            List<Integer> input = fracMulFracMulFracAdd(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5)) + input.get(6);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " + " + input.get(6).toString() + " = ?";
        }
        else if(choice == 1){

            List<Integer> input = fracMulFracMulFracSub(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5)) - input.get(6);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " - " + input.get(6).toString() + " = ?";
            }
        else if(choice == 2){

            List<Integer> input = fracMulFracAddFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) + (input.get(4) * input.get(6)) / (input.get(5) * input.get(7));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " + " + input.get(4).toString() + "/" + input.get(5).toString() + " x " + input.get(6).toString() + "/" + input.get(7).toString() + " = ?";
        }
        else if(choice == 3){

            List<Integer> input = fracMulFracSubFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) - (input.get(4) * input.get(6)) / (input.get(5) * input.get(7));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " - " + input.get(4).toString() + "/" + input.get(5).toString() + " x " + input.get(6).toString() + "/" + input.get(7).toString() + " = ?";
        }
        else{

            List<Integer> input = fracMulFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " = ?";
        }
    }


    public String getQuestion(){
        return this.question;
    }

    public int getAnswer(){
        return this.answer;

    }


//**LEVEL 1 QUESTIONS***\\

    //Question of the form "a + b = c"
    private List<Integer> addQuestion(){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(10);

            Random randB = new Random();
            int b = randB.nextInt(10);

            if(a + b < 10){

                input.add(a);
                input.add(b);
                go = true;
            }
        }

        Collections.shuffle(input);
        return input;

    }

    //Question of the form "a - b = c"
    private List<Integer> subQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit);

            if(a - b < 10 && a - b >= 0){

                input.add(a);
                input.add(b);
                go = true;

            }

        }
        return input;
    }




//***LEVEL 2 QUESTIONS***\\

    //Question of the form "a - b - c = d"
    private List<Integer> subSubQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit);

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            if(a - b - c < 10 && a - b - c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }
        }

        return input;
    }

    //Question of the form "a + b - c = d"
    private List<Integer> addSubQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit);

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            if(a + b - c < 10 && a + b - c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;
            }
        }
        return input;
    }

    //Question of the form "a - b + c = d"
    private List<Integer> subAddQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit);

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            if(a - b + c < 10 && a - b + c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a x b = c"
    private List<Integer> mulQuestion(){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(10 - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(10 - 1) + 1;

            if(a * b < 10){

                input.add(a);
                input.add(b);

                go = true;
            }
        }

        Collections.shuffle(input);
        return input;

    }



//***lEVEL 3 QUESTIONS***\\

    //Question of the form "a x b + c = d"
    private List<Integer> mulAddQuestion(){
        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(10 - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(10 - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(10);

            if(a * b + c < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a + b x c = d"
    private List<Integer> addMulQuestion(){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(10);

            Random randB = new Random();
            int b = randB.nextInt(10 - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(10 - 1) + 1;

            if(a + b * c < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;

    }

    //Question of the form "a x b - c = d"
    private List<Integer> mulSubQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            if(a * b - c < 10 && a * b - c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;

    }

    //Question of the form "a - b x c = d"
    private List<Integer> subMulQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit - 1) + 1;

            if(a - b * c < 10 && a - b * c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }

  //Question of the form "a / b = c"
    private List<Integer> divQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            if(a / b < 10 && a % b == 0){
                input.add(a);
                input.add(b);

                go = true;
            }
        }

        return input;
    }




//***LEVEL 4 QUESTIONS***\\

    //Question of the form "a/b + c - d = e"
    private List<Integer> fracAddSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit);

            if(a / b + c - d < 10 && a / b + c - d >= 0 && a % b == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                go = true;

            }

        }

        return input;

    }

    //Question of the form "a/b - c + d = e"
    private List<Integer> fracSubAdd(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit);

            if(a / b - c + d < 10 && a / b - c + d >= 0 && a % b == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                go = true;

            }

        }

        return input;

    }

    //Question of the form "a/b - c = d"
    private List<Integer> fracSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);


            if(a / b - c < 10 && a / b - c >= 0 && a % b == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a/b - c - d = e"
    private List<Integer> fracSubSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit);

            if(a / b - c - d < 10 && a / b - c - d >= 0 && a % b == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a/b x c/d = e"
    private List<Integer> fracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit - 1) + 1;

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            if((a*c)%(b*d) == 0 && (a*c)/(b*d) < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                go = true;

            }

        }

        return input;
    }



//***LEVEL 5 QUESTIONS***\\

    //Question of the form "a/b * c/d + e = f"
    private List<Integer> fracMulFracAdd(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit - 1) + 1;

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(10);

            if((a*c)%(b*d) == 0 && (a*c) / (b*d) + e < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a/b * c/d - e = f"
    private List<Integer> fracMulFracSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit - 1) + 1;

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            if((a*c)%(b*d) == 0 && (a*c) / (b*d) - e < 10 && (a*c) / (b*d) - e >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                go = true;

            }

        }

        return input;

    }

    //Question of the form "a/b + c - d/e = f"
    private List<Integer> fracAddSubFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit - 1) + 1;

            if(a % b == 0 && d % e == 0 && a/b + c - d/e < 10 && a/b + c - d/e >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                go = true;

            }

        }

        return input;


    }

    //Question of the form "a/b - c + d/e = f"
    private List<Integer> fracSubAddFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit - 1) + 1;

            if(a % b == 0 && d % e == 0 && a/b - c + d/e < 10 && a/b - c + d/e >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a * b/c * d/e = f"
    private List<Integer> mulFracMulFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit - 1) + 1;

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit - 1) + 1;

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit - 1) + 1;

            if((a*b*d) % (c*e) == 0 && (a*b*d) / (c*e) < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                go = true;

            }

        }

        return input;
    }



//***LEVEL 6 QUESTIONS***\\

    //Question of the form "a/b * c/d * e/f + g = h"
    private List<Integer> fracMulFracMulFracAdd(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            Random randF = new Random();
            int f = randF.nextInt(uLimit - 1) + 1;

            Random randG = new Random();
            int g = randG.nextInt(10);

            if((a*c*e) % (b*d*f) == 0 && (a*c*e) / (b*d*f) + g < 10 ){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                input.add(f);
                input.add(g);
                go = true;

            }

        }

        return input;
    }

  //Question of the form "a/b * c/d * e/f - g = h"
    private List<Integer> fracMulFracMulFracSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            Random randF = new Random();
            int f = randF.nextInt(uLimit - 1) + 1;

            Random randG = new Random();
            int g = randG.nextInt(uLimit);

            if((a*c*e) % (b*d*f) == 0 && (a*c*e) / (b*d*f) - g < 10 ){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                input.add(f);
                input.add(g);
                go = true;

            }

        }

        return input;
    }

  //Question of the form "a/b * c/d + e/f * g/h = i"
    private List<Integer> fracMulFracAddFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            Random randF = new Random();
            int f = randF.nextInt(uLimit - 1) + 1;

            Random randG = new Random();
            int g = randG.nextInt(uLimit);

            Random randH = new Random();
            int h = randH.nextInt(uLimit - 1) + 1;

            if((a*c) % (b*d) == 0 && (e*g) % (f*h) == 0 && (a*c) / (b*d) + (e*g) / (f*h) < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                input.add(f);
                input.add(g);
                input.add(h);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a/b * c/d - e/f + g/h = i"
    private List<Integer> fracMulFracSubFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            Random randF = new Random();
            int f = randF.nextInt(uLimit - 1) + 1;

            Random randG = new Random();
            int g = randG.nextInt(uLimit);

            Random randH = new Random();
            int h = randH.nextInt(uLimit - 1) + 1;

            if((a*c) % (b*d) == 0 && (e*g) % (f*h) == 0 && (a*c) / (b*d) - (e*g) / (f*h) < 10 && (a*c) / (b*d) - (e*g) / (f*h) >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                input.add(f);
                input.add(g);
                input.add(h);
                go = true;

            }

        }

        return input;
    }

  //Question of the form "a/b * c/d * e/f = g"
    private List<Integer> fracMulFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            Random randA = new Random();
            int a = randA.nextInt(uLimit);

            Random randB = new Random();
            int b = randB.nextInt(uLimit - 1) + 1;

            Random randC = new Random();
            int c = randC.nextInt(uLimit);

            Random randD = new Random();
            int d = randD.nextInt(uLimit - 1) + 1;

            Random randE = new Random();
            int e = randE.nextInt(uLimit);

            Random randF = new Random();
            int f = randF.nextInt(uLimit - 1) + 1;

            if((a*c*e) % (b*d*f) == 0 && (a*c*e) / (b*d*f) < 10){

                input.add(a);
                input.add(b);
                input.add(c);
                input.add(d);
                input.add(e);
                input.add(f);
                go = true;

            }

        }

        return input;
    }



}

