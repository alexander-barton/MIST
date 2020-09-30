package ch.makery.address.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestQuestion {

    private int answer;
    private String question;
    private int baseTime;
    private Integer diffLevel;

    //Constructor, creates the question based on the difficulty level entered;
    public TestQuestion(int difficulty, int uLimit) {

       switch(difficulty){


       //difficulty level of 1
       case 1:


           //randomly chooses 1 of Level 1 - 2
           int choice = ThreadLocalRandom.current().nextInt(2);

           switch(choice){

           case 0:
               questionLevel1(uLimit);
               break;
           case 1:
               questionLevel2(uLimit);
               break;
           }

           break;


           /*
            *
            *  EDITS FOR ALEXANDRA
            *  MAKES LEVEL 1 THE CHOICES FOR
            *
            *
            questionLevel1(uLimit);
            break;
            */


       //difficulty level of 2
       case 2:

           //randomly chooses 1 of Level 1 - 3
           int choice2 = ThreadLocalRandom.current().nextInt(3);

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
           }

           break;


       //difficulty level of 3
       case 3:

           //randomly chooses 1 of Level 1 - 4
           int choice3 = ThreadLocalRandom.current().nextInt(4);

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
           }

           break;

       //difficulty level of 4
       case 4:

           //randomly chooses 1 of Level 1 - 5
           int choice4 = ThreadLocalRandom.current().nextInt(5);

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
           }

           break;


       //difficulty level of 5
       case 5:

           //randomly chooses 1 of Level 1 - 6
           int choice5 = ThreadLocalRandom.current().nextInt(6);

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
           case 5:
               questionLevel1(uLimit);
               break;
           }
           break;


       //difficulty level of 6
       case 6:

           //randomly chooses 1 of Level 1 - 7
           int choice6 = ThreadLocalRandom.current().nextInt(7);

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
           case 4:
               questionLevel7(uLimit);
               break;
           case 5:
               questionLevel1(uLimit);
               break;
           case 6:
               questionLevel2(uLimit);
               break;
           }

           break;
       }

    }

    /*
     *
     * questionLevel1 for Alexandra


    private void questionLevel1(int uLimit){
        this.diffLevel = 1;
        int choice = ThreadLocalRandom.current().nextInt(3);
        this.baseTime = 1800;

        if(choice == 0){
            List<Integer> input = addQuestion();
            this.answer = input.get(0) + input.get(1);
            this.question = input.get(0).toString() + " + " + input.get(1).toString() + " = ?";
        }
        else if(choice == 1){
            List<Integer> input = subQuestion(uLimit);
            this.answer = input.get(0) - input.get(1);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " = ?";
        }
        else{
            List<Integer> input = mulQuestion();
            this.answer = input.get(0) * input.get(1);
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + " = ?";
        }
    }

    */



    private void questionLevel1(int uLimit){

        this.diffLevel = 1;
        int choice = ThreadLocalRandom.current().nextInt(2);
        this.baseTime = 1800;

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

        this.diffLevel = 2;
        int choice = ThreadLocalRandom.current().nextInt(2);
        this.baseTime = 2100;

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

        else{

            List<Integer> input = subAddQuestion(uLimit);
            this.answer = input.get(0) - input.get(1) + input.get(2);
            this.question = input.get(0).toString() + " - " + input.get(1).toString() + " + " + input.get(2).toString() + " = ?";

        }


    }

    private void questionLevel3(int uLimit){

        this.diffLevel = 3;
        int choice = ThreadLocalRandom.current().nextInt(4);
        this.baseTime = 2400;

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

            List<Integer> input = mulQuestion();
            this.answer = input.get(0) * input.get(1);
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + " = ?";

        }

    }

    private void questionLevel4(int uLimit){

        this.diffLevel = 4;
        int choice = ThreadLocalRandom.current().nextInt(6);
        this.baseTime = 2600;

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
        else if(choice == 4){

            List<Integer> input = divQuestion(uLimit);
            this.answer = input.get(0) / input.get(1);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " = ?";
        }
        else if(choice == 5){

            List<Integer> input = addFrac(uLimit);
            this.answer = input.get(0) + (input.get(1) / input.get(2));
            this.question = input.get(0).toString() + " + " + input.get(1) + "/" + input.get(2) + " = ?";
        }
        else{

            List<Integer> input = subFrac(uLimit);
            this.answer = input.get(0) - (input.get(1) / input.get(2));
            this.question = input.get(0).toString() + " - " + input.get(1) + "/" + input.get(2) + " = ?";
        }

    }

    private void questionLevel5(int uLimit){

        this.diffLevel = 5;
        int choice = ThreadLocalRandom.current().nextInt(2);
        this.baseTime = 3300;

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
        else{

            List<Integer> input = fracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " = ?";
        }
    }

    private void questionLevel6(int uLimit){

        this.diffLevel = 6;
        int choice = ThreadLocalRandom.current().nextInt(3);
        this.baseTime = 4300;

        if(choice == 0){

            List<Integer> input = fracAddSubFrac(uLimit);
            this.answer = (input.get(0) / input.get(1)) + input.get(2) - (input.get(3) / input.get(4));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " + " + input.get(2).toString() + " - " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";
        }
        else if(choice == 1){

            List<Integer> input = fracSubAddFrac(uLimit);
            this.answer = (input.get(0) / input.get(1)) - input.get(2) + (input.get(3) / input.get(4));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + " + " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";
            }
        else if(choice == 2){

            List<Integer> input = mulFracMulFrac(uLimit);
            this.answer = (input.get(0) * input.get(1) * input.get(3)) / (input.get(2) * input.get(4));
            this.question = input.get(0).toString() + " x " + input.get(1).toString() + "/" + input.get(2).toString() + " x " + input.get(3).toString() + "/" + input.get(4).toString() + " = ?";
        }
        else{

            List<Integer> input = fracMulFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString() + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " = ?";
        }
    }

    private void questionLevel7(int uLimit){

        this.diffLevel = 7;
        int choice = ThreadLocalRandom.current().nextInt(5);
        this.baseTime = 5800;

        if(choice == 0){

            List<Integer> input = fracMulFracSubFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) - (input.get(4) * input.get(6)) / (input.get(5) * input.get(7));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString()
                    + " - " + input.get(4).toString() + "/" + input.get(5).toString() + " x " + input.get(6).toString() + "/" + input.get(7).toString()
                    + " = ?";
        }
        else if(choice == 1){

            List<Integer> input = fracMulFracAddFracMul(uLimit);
            this.answer = (input.get(0) * input.get(2)) / (input.get(1) * input.get(3)) + (input.get(4) * input.get(6)) / (input.get(5) * input.get(7));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString()
                    + " + " + input.get(4).toString() + "/" + input.get(5).toString() + " x " + input.get(6).toString() + "/" + input.get(7).toString()
                    + " = ?";
        }
        else if(choice == 2){

            List<Integer> input = fracSubFracMulFrac(uLimit);
            this.answer = input.get(0) / input.get(1) - (input.get(2) * input.get(4)) / (input.get(3) * input.get(5));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " - " + input.get(2).toString() + "/" + input.get(3).toString() +
                    " x " + input.get(4).toString() + "/" + input.get(5).toString() + " = ?";
        }
        else if(choice == 3){

            List<Integer> input = fracAddFracMulFrac(uLimit);
            this.answer = input.get(0) / input.get(1) + (input.get(2) * input.get(4)) / (input.get(3) * input.get(5));
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " + " + input.get(2).toString() + "/" + input.get(3).toString() +
                    " x " + input.get(4).toString() + "/" + input.get(5).toString() + " = ?";

        }
        else if(choice == 4){

            List<Integer> input = fracMulFracMulFracAdd(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5)) + input.get(6);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString()
                    + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " + " + input.get(6).toString() + " = ?";
        }
        else{

            List<Integer> input = fracMulFracMulFracSub(uLimit);
            this.answer = (input.get(0) * input.get(2) * input.get(4)) / (input.get(1) * input.get(3) * input.get(5)) - input.get(6);
            this.question = input.get(0).toString() + "/" + input.get(1).toString() + " x " + input.get(2).toString() + "/" + input.get(3).toString()
                    + " x " + input.get(4).toString() + "/" + input.get(5).toString() + " - " + input.get(6).toString() + " = ?";
        }
    }

    public String getQuestion(){
        return this.question;
    }

    public int getAnswer(){
        return this.answer;
    }

    public int getBaseTime(){
        return this.baseTime;
    }


  //***********************\\
 //****LEVEL 1 QUESTIONS****\\
//***************************\\



    //Question of the form "a + b = c"
    private List<Integer> addQuestion(){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(10);

            int b = ThreadLocalRandom.current().nextInt(10);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit);

            int b = ThreadLocalRandom.current().nextInt(uLimit);

            if(a - b < 10 && a - b >= 0){

                input.add(a);
                input.add(b);
                go = true;

            }

        }
        return input;
    }



  //***********************\\
 //****LEVEL 2 QUESTIONS****\\
//***************************\\



    //Question of the form "a - b - c = d"
    private List<Integer> subSubQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit);
            int c = ThreadLocalRandom.current().nextInt(uLimit);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit);
            int c = ThreadLocalRandom.current().nextInt(uLimit);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit);
            int c = ThreadLocalRandom.current().nextInt(uLimit);

            if(a - b + c < 10 && a - b + c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }



  //***********************\\
 //****LEVEL 3 QUESTIONS****\\
//***************************\\

    //Question of the form "a x b = c"
    private List<Integer> mulQuestion(){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(10 - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(10 - 1) + 1;

            if(a * b < 10){

                input.add(a);
                input.add(b);

                go = true;
            }
        }

        Collections.shuffle(input);
        return input;

    }

    //Question of the form "a x b + c = d"
    private List<Integer> mulAddQuestion(){
        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(10 - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(10 - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(10);

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

            int a = ThreadLocalRandom.current().nextInt(10);
            int b = ThreadLocalRandom.current().nextInt(10 - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(10 - 1) + 1;

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

            if(a - b * c < 10 && a - b * c >= 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }



  //***********************\\
 //****LEVEL 4 QUESTIONS****\\
//***************************\\

    //Question of the form "a/b = c"
    private List<Integer> divQuestion(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

            if(a / b < 10 && a % b == 0){
                input.add(a);
                input.add(b);

                go = true;
            }
        }

        return input;
    }

    //Question of the form "a/b + c - d = e"
    private List<Integer> fracAddSub(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);


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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit);

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

    //Question of the form "a + b/c = d"
    private List<Integer> addFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;


            if(a + b / c < 10 && b % c == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }

    //Question of the form "a - b/c = d"
    private List<Integer> subFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;


            if(a - b / c < 10 && a - b / c >= 0 && b % c == 0){

                input.add(a);
                input.add(b);
                input.add(c);
                go = true;

            }

        }

        return input;
    }




  //***********************\\
 //****LEVEL 5 QUESTIONS****\\
//***************************\\



    //Question of the form "a/b x c/d = e"
    private List<Integer> fracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

    //Question of the form "a/b * c/d + e = f"
    private List<Integer> fracMulFracAdd(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(10);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit);

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


  //***********************\\
 //****LEVEL 6 QUESTIONS****\\
//***************************\\



    //Question of the form "a/b + c - d/e = f"
    private List<Integer> fracAddSubFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

    //Question of the form "a/b * c/d * e/f = g"
    private List<Integer> fracMulFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit);
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit);
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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


  //***********************\\
 //****LEVEL 7 QUESTIONS****\\
//***************************\\


    //Question of the form "a/b * c/d - e/f * g/h = i"
    private List<Integer> fracMulFracSubFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int g = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int h = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

    //Question of the form "a/b * c/d + e/f * g/h = i"
    private List<Integer> fracMulFracAddFracMul(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int g = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int h = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

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

    //Question of the form "a/b - c/d * e/f = g"
    private List<Integer> fracSubFracMulFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

            if(a % b == 0 && (c*e) % (d*f) == 0 && (a/b) - (c*e) / (d*f) < 10 && (a/b) - (c*e) / (d*f) >= 0){

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

    //Question of the form "a/b + c/d * e/f = g"
    private List<Integer> fracAddFracMulFrac(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit);
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;

            if(a % b == 0 && (c*e) % (d*f) == 0 && (a/b) + (c*e) / (d*f) < 10){

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

    //Question of the form "a/b * c/d * e/f + g = h"
    private List<Integer> fracMulFracMulFracAdd(int uLimit){

        boolean go = false;
        List<Integer> input = new ArrayList<>();

        while(!go){

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int g = ThreadLocalRandom.current().nextInt(10);

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

            int a = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int b = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int c = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int d = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int e = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int f = ThreadLocalRandom.current().nextInt(uLimit - 1) + 1;
            int g = ThreadLocalRandom.current().nextInt(uLimit);

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

    public Integer getDiffLevel(){
        return this.diffLevel;
    }
}

