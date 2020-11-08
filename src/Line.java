/*
 *This project is created on IntelliJ IDEA
 *
 */

/**
 * @author khom
 */


public class Line {
    private int id;
        private static int count = 0;
        private boolean isBusy;
        private String state;

        Line(){
            id = count;
            count++;

            isBusy = false;
            this.state = "idle";
        }

        public String getState(){
            return this.state;
        }

        public int getId(){
            return this.id;
        }

        public void setState(String state){
            this.state = state;
        }

        public String toString(){
            return "Line ID: " + id + ", in state: " + state;
        }
}

/**
 * @copyright: github.com/KhomZ
 * code my name in the console of your heart then see what happens next (~_~)
 */
