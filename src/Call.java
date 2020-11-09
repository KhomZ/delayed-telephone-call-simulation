/*
*
*This class is a model of a call.
 *
 * */

//I created some changes here.


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.lang.String;

public class Call {

    private Line fromLine = null;
    private Line toLine = null;
    private Random random;
    private long duration;
    private long admittedTimestamp;  //timestamp when the call is connected with the lines
    private long arrivalTimestamp;

    public long getArrivalTimestamp(){
        return arrivalTimestamp;
    }

    public long getDuration(){
        return duration;
    }

    public Line getFromLine(){
        return fromLine;
    }

    public void setFromLine(Line fromLine){
        this.fromLine = fromLine;
    }

    public Line getToLine(){
        return this.toLine;
    }

    public void setToLine(Line toLine){
        this.toLine = toLine;
    }

    public void setAdmittedTimestamp(long a){
        this.admittedTimestamp = a;
    }

    public long getAdmittedTimestamp(){
        return this.admittedTimestamp;
    }

    //Constructor definition section
    Call(Line[] line, int maxTalkTime){
        random = new Random();
        do {
            this.fromLine = line[random.nextInt(line.length)];  // random available line selected for the call
            this.toLine = line[random.nextInt(line.length)];
        }while(this.toLine.equals(this.fromLine));
        Date date = new Date();
        arrivalTimestamp = date.getTime();  //This gives the current system timestamp
        this.duration = 1000*(long)random.nextInt(maxTalkTime);  //select random duration for the call
        this.admittedTimestamp = 0;

    }

    //Now, here comes the method that will be called by a call in order to connect to some lines
    public int connect(ArrayList callsInProgress, ArrayList delayedCalls){
        if(fromLine.getState().equals("busy") || toLine.getState().equals("busy") || callsInProgress.size() >= TelephoneCallSimulation.NUMBER_OF_LINKS){
            System.out.println(">>" + new Date() + "Added to delay call list: " + this); //printing the log
            delayedCalls.add(this);
            return -1;
        }else{
            this.admittedTimestamp = System.currentTimeMillis();
            callsInProgress.add(this);
            System.out.println(">>" + new Date() + "Added to Progress: " + this);
            fromLine.setState("busy");
            toLine.setState("busy");
            return 1;
        }

        //public String toString(){
        /**
        public String toString() {
            if(this.fromLine.equals(null) || this.toLine.equals(null)){
                return "Call cannot be connected";
            }else{
                return "Call From " + this.fromLine.getId() + " To " + this.toLine.getId() + " Arrived at " + new Date(this.arrivalTimestamp) + " for duration " + (duration/1000) + ((this.admittedTimestamp != 0) ? " Admitted at " + (new Date(this.arrivalTimestamp)) : " not admitted ");
            }
        }

        public static void main(String[] args){
            if(this.fromLine.equals(null) || this.toLine.equals(null)){
                return "Call cannot be connected";
            }else{
                return "Call From " + this.fromLine.getId() + " To " + this.toLine.getId() + " Arrived at " + new Date(this.arrivalTimestamp) + " for duration " + (duration/1000) + ((this.admittedTimestamp != 0) ? " Admitted at " + (new Date(this.arrivalTimestamp)) : " not admitted ");
            }
        }
         */




    }
    public String toString() {
        if(this.fromLine.equals(null) || this.toLine.equals(null)){
            return "Call cannot be connected";
        }else{
            return "Call From " + this.fromLine.getId() + " To " + this.toLine.getId() + " Arrived at " + new Date(this.arrivalTimestamp) + " for duration " + (duration/1000) + ((this.admittedTimestamp != 0) ? " Admitted at " + (new Date(this.arrivalTimestamp)) : " not admitted ");
        }
    }

}

/**
 *@khom khomcodes
 */