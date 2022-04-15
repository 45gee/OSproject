import java.util.*;

class arrivalTimeComparator implements Comparator<PCB>{
              
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(PCB PCB1, PCB PCB2) {
                if (PCB1.arrivalTime < PCB2.arrivalTime)
                    return 1;
                else if (PCB1.arrivalTime > PCB2.arrivalTime)
                    return -1;
                                return 0;
                }
}

class burstTimeComparator implements Comparator<PCB>{
              
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(PCB PCB1, PCB PCB2) {
                if (PCB1.burstTime < PCB2.burstTime)
                    return 1;
                else if (PCB1.burstTime > PCB2.burstTime)
                    return -1;
                                return 0;
                }
}



public class PCB {
   int processID;
   int arrivalTime;
   int priority;
   int burstTime; //CPU burst time
   int startTime;
   int terminationTime;
   int roundTime; //turn around time
   int waitingTime;
   int responseTime;
   //int PNUMBER =0; //id counter
   
   public PCB(){
      this.processID=0;
      this.arrivalTime=0;
      this.priority=0;
      this.burstTime=0;
      this.startTime=0;
      this.terminationTime=0;
      this.roundTime=0;
      this.waitingTime=0;
      this.responseTime=0;
      }
     
     
   public PCB(int processID ,int arrivalTime, int burstTime, int priority){
      this.processID=processID;
      this.arrivalTime=arrivalTime;
      this.priority=priority;
      this.burstTime=burstTime;
      this.startTime=0;
      this.terminationTime=0;
      this.roundTime=0;
      this.waitingTime=0;
      this.responseTime=0;
      }
      
 public void setresponseTime(int responseTime){
      this.responseTime=responseTime;
   }//end setter
    public int getresponseTime(){
      return responseTime;
   }
     
 public void setroundTime(int roundTime){
      this.roundTime=roundTime;
   }//end setter
    public int getroundTime(){
      return roundTime;
   }
          
 public void setterminationTime(int terminationTime){
      this.terminationTime=terminationTime;
   }//end setter
    public int getterminationTime(){
      return terminationTime;
   }
         
 public void setwaitingTime(int waitingTime){
      this.waitingTime=waitingTime;
   }//end setter
    public int getwaitingTime(){
      return waitingTime;
   }
     
 public void setstartTime(int startTime){
      this.startTime=startTime;
   }//end setter
    public int getstartTime(){
      return startTime;
   }
    
 public void setProcessID(int processID){
      this.processID=processID;
   }//end setter
    public int getProcessID(){
      return processID;
   }
   /////
   public void setPriority(int priority) throws Exception{
   if (priority ==1 || priority==2)
         this.priority=priority;
         else
         throw new Exception ("priority must be 1 or 2");
   }//end setter
    public int getPriority(){
      return priority;
   }
   ///////

   public void setCpuBurst(int burstTime){
      this.burstTime=burstTime;
   }//
   public int getCpuBurst(){
     return burstTime;
   }//

   public void setArrivalTime(int arrivalTime){
   this.arrivalTime=arrivalTime;}
   
 public int getArrivalTime(){
   return arrivalTime;}

 public PCB(PCB pcb){
      this.processID=pcb.getProcessID();
      this.arrivalTime=pcb.getArrivalTime();
      this.priority=pcb.getPriority();
      this.burstTime=pcb.getCpuBurst();
      this.startTime=pcb.getstartTime();
      this.terminationTime=pcb.getterminationTime();
      this.roundTime=pcb.getroundTime();
      this.waitingTime=pcb.getwaitingTime();
      this.responseTime=pcb.getresponseTime();
      }


}//end of class
