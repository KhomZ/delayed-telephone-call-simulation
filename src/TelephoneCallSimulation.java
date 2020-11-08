/*
 *this is TelephoneCallSimulation class.
 */

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
//import javax.swing.*;

/**
 * @author khom
 */
public class TelephoneCallSimulation {
    private static final int numOfLines = 8;
    //private static final int NUMBER_OF_LINKS = 3;
    public static final int NUMBER_OF_LINKS = 3;
    private static final int maxTalkTime = 20;

    static CallsOnProgressList callsInProgress;
    public static ArrayList<Call> delayedCallList;

    static Line[] line;
    static Timer timer = new Timer();

    public TelephoneCallSimulation(){
        /*

         */
    }
    static class GenerateRandomCall extends TimerTask{
        @Override
        public void run(){
            //creating a random call
            Call call;
            do{
                call = new Call(line, maxTalkTime);
            }while(call.getFromLine().getState().equals("busy"));
            System.out.println("\n\n\nCURRENT TIME: " + new Date());
            System.out.println("\n>> A Call came from : " + call.getFromLine().getId() + " To " + call.getToLine().getId() + " at " + new Date(call.getArrivalTimestamp()) + " which has duration of " + call.getDuration()/1000);

            //connecting to a call
            call.connect(callsInProgress, delayedCallList);
            printLists();  //check out this once, i think there's something missing out there. finally i got it and edited this

            int delay = (1 + new Random().nextInt(10)*1000);
            timer.schedule(new GenerateRandomCall(), delay);
        }
    }

    static void printLists(){
        System.out.println("\nIn Progress List: ");
        for(Object c: callsInProgress){
            //System.out.println((Call)c); //why is this casting not working
            System.out.println(c);
        }
        System.out.println("\nDelayed List: ");
        for(Call c: delayedCallList){
            System.out.println(c);
        }
    }


    /**
     * @param args the command line arguments.
     */

    public static void main(String[] args){
        //this is how we create 8 idle lines
        line = new Line[numOfLines];
        for(int i=0; i < numOfLines; i++){
            line[i] = new Line();
        }

        //let's create a list of calls as 'Calls in progress'
        callsInProgress = new CallsOnProgressList();


        //now creating a list for the delayed calls
        //delayedCallList = new ArrayList<Call>(); //Explicit type argument Call can be replaced with <>
        delayedCallList = new ArrayList<>();

        new GenerateRandomCall().run();
    }
}
