import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.*;

public class OSphase1_ {
static Scanner input = new Scanner(System.in);
   static int numOfP=0;
   static PCB[] PCBarray = null;
   static PCB[] Q1  = null;
   static PCB[] Q2  = null;
   static int numOfQ1=0;
   static int numOfQ2=0;

   static int PNUM=0;
   static int RNUM=0;
   static int maxArrivalQ1=0;
   static int maxArrivalQ2=0;
   
   
   /*GO TO MAIN, last one*/


///////////////////////////////////////////////////////////////////  
///////////////////////////Case 1//////////////////////////////////
/////////////////////////////////////////////////////////////////// 

public static void case1() {

   System.out.println("Please enter the total number of processes in the system");
   numOfP = input.nextInt();
   PCBarray = new PCB[numOfP];
   for (int i = 0; i < numOfP; i++) 
     {
       PCBarray[i] = new PCB();
   
       PCBarray[i].setProcessID(PNUM++);
   
       System.out.println("Please enter the priority for process #" + (i + 1) + ": ");
       int priority;
   
       while (true) 
         {
           try {
             priority = input.nextInt();
             PCBarray[i].setPriority(priority);
             break;
   
           } catch (Exception e) {
             System.out.println(e.getMessage());}
         }
   
       System.out.println("Please enter the CPU burst time for process #" + (i + 1) + ":");
       int cpuBurst = input.nextInt();
       PCBarray[i].setCpuBurst(cpuBurst);
   
       System.out.println("Please enter the process arrival time for process #" + (i + 1) + ":");
       int arrivalTime = input.nextInt();
       PCBarray[i].setArrivalTime(arrivalTime);
     }
     
   for(int i=0;i<numOfP;i++){
     if(PCBarray[i].getPriority()==1)
     numOfQ1++;
     else if(PCBarray[i].getPriority()==2)
     numOfQ2++;}
   
     Q2=new PCB[numOfQ2];
     Q1=new PCB[numOfQ1];
     int i2=0;
     int i1=0;
     
     for(int i=0;i<numOfP;i++){
       if(PCBarray[i].getPriority()==1){
         Q1[i1]=PCBarray[i];
         i1++;}
       else{
         Q2[i2]=PCBarray[i];
         i2++;}
       }
   
     for(int i=0;i<Q1.length;i++){
       int temp=Q1[i].arrivalTime;
       if(maxArrivalQ1<temp)
         maxArrivalQ1=temp;}
   
     for(int i=0;i<Q2.length;i++){
       int temp=Q2[i].arrivalTime;
       if(maxArrivalQ2<temp)
         maxArrivalQ2=temp;}
   
   
   }//end case 1
   
static class arrivalTimeComparator implements Comparator<PCB>{
              
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(PCB PCB1, PCB PCB2) {
                if (PCB1.arrivalTime > PCB2.arrivalTime)
                    return 1;
                else if (PCB1.arrivalTime < PCB2.arrivalTime)
                    return -1;
                                return 0;
                }
    }//enda class arrivalTimeComparator

static class burstTimeComparator implements Comparator<PCB>{
              
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(PCB PCB1, PCB PCB2) {
                if (PCB1.burstTime > PCB2.burstTime)
                    return 1;
                else if (PCB1.burstTime < PCB2.burstTime)
                    return -1;
                                return 0;
                }
    }//end class burstTimeComparator


///////////////////////////////////////////////////////////////////  
///////////////////////////Case 3//////////////////////////////////
/////////////////////////////////////////////////////////////////// 

public static void case3() {

   PriorityQueue<PCB>[] PQ1array = new PriorityQueue[maxArrivalQ1+1];
   PriorityQueue<PCB> PQ2 = new PriorityQueue<PCB>(Q2.length+1 ,new arrivalTimeComparator() );
   
   if(!(Q1.length==0) ){
   
      for(int i=0;i<PQ1array.length;i++)
        PQ1array[i] = new PriorityQueue<PCB>(numOfQ1, new burstTimeComparator());            
      
      for(int i=0;i<numOfQ1;i++){
         PCB temp = Q1[i];
         PQ1array[temp.getArrivalTime()].add(temp);}
      ////to print q1
      /*for(int i=0;i<PQ1array.length;i++){
        if(!PQ1array[i].isEmpty()){
      
      while(!PQ1array[i].isEmpty()){
        PCB cur=PQ1array[i].poll();
        System.out.println(cur.getProcessID());}}}*/ }
      
   ///Q2
   if(!(Q2.length==0) ){
     for(int i=0;i<Q2.length;i++)
       PQ2.add(Q2[i]);
   
   /*for(int i=0;i<Q2.length;i++){
       PCB temp =PQ2.poll();
     System.out.println(temp.getProcessID());}*/ }
   
   // 
   
   int timer =0;
   PCB[] result =new PCB[numOfP];  
   int arrtime =0;
   int countP=numOfP;
   int counter=0;
   
   for(int k=0;k<PQ1array.length;k++){
   
      if(! (PQ1array[k].isEmpty()) ){
         while(! (PQ1array[k].isEmpty()) ){
         PCB temp =PQ1array[k].poll();
         temp.setstartTime(timer);
         temp.setresponseTime(timer);
         temp.setwaitingTime(temp.getstartTime() - temp.getArrivalTime());
         temp.setterminationTime(temp.getstartTime() + temp.getCpuBurst() );
         temp.setroundTime( temp.getterminationTime() - temp.getArrivalTime() );
         timer+=temp.getCpuBurst()-1;
         result[counter]=temp;
         counter++;}  }
   
      timer++;}
   
   for(int i=0;i<numOfP;i++){
      System.out.println("ID: " + result[i].getProcessID() + "startTime:"+result[i].getstartTime()+ "waitingTime:"+result[i].getwaitingTime()
      + "terminationTime:"+result[i].getterminationTime()+ "responseTime:"+result[i].getresponseTime()+ "roundTime:"+result[i].getroundTime());}
      
      
   
}//end case 3

///////////////////////////////////////////////////////////////////  
///////////////////////////Case 2//////////////////////////////////
///////////////////////////////////////////////////////////////////  

public static void case2() {
   System.out.println("_____________________________________");
   System.out.println("--------------Q1----------------------");
   for(int i=0;i<numOfQ1;i++)
     System.out.println("#"+(i+1)+" process ID: "+Q1[i].getProcessID()+"|priority:"+Q1[i].getPriority()+"|CPU burst time:"+Q1[i].getCpuBurst()+"|arrival time:"+Q1[i].getArrivalTime());
   
   System.out.println("_____________________________________");
   System.out.println("--------------Q2----------------------");
   for(int i=0;i<numOfQ2;i++)
     System.out.println("#"+(i+1)+" process ID: "+Q2[i].getProcessID()+"|priority:"+Q2[i].getPriority()+"|CPU burst time:"+Q2[i].getCpuBurst()+"|arrival time:"+Q2[i].getArrivalTime());
   
   try (FileWriter f = new FileWriter("Report1.txt", true);
     BufferedWriter b = new BufferedWriter(f);
     PrintWriter p = new PrintWriter(b);) {
   
     p.println("Report number -"+(++RNUM)+"-");
     p.println("_____________________________________");
     p.println("--------------Q1----------------------");
     for(int i=0;i<numOfQ1;i++)
       p.println("#"+(i+1)+" process ID: "+Q1[i].getProcessID()+"|priority:"+Q1[i].getPriority()+"|CPU burst time:"+Q1[i].getCpuBurst()+"|arrival time:"+Q1[i].getArrivalTime());
   
     p.println("--------------Q2----------------------");
     for(int i=0;i<numOfQ2;i++)
       p.println("#"+(i+1)+" process ID: "+Q2[i].getProcessID()+"|priority:"+Q2[i].getPriority()+"|CPU burst time:"+Q2[i].getCpuBurst()+"|arrival time:"+Q2[i].getArrivalTime());
   }catch (IOException i) { i.printStackTrace(); } }// ends case 2


///////////////////////////////////////////////////////////////////
///////////////////////////Main////////////////////////////////////
///////////////////////////////////////////////////////////////////

   public static void main(String[] args) throws Exception
    {  
        boolean stop = false;
        do
        {    
            System.out.println("\n\t\t\t **** OS Schedule **** \n");
            System.out.println("Welcome to the Operating Systems Schedule!"+
                                "\n\nPlease choose a number from the menu:\n"+
                                "\n1. Enter process' information"+
                                "\n2. Report detailed information about each process"+
                                "\n3. Report the average turnaround time, waiting time, and response time"+
                                "\n4. Exit");
           
            Scanner obj = new Scanner(System.in);
            int choice = obj.nextInt();
           
            switch (choice)
            {
                case 1:
                    System.out.println("\n\t **** Enter process' information **** ");
                    case1();
                    break;
                case 2:
                    System.out.println("\n\t **** Report detailed information about each process **** ");
                    case2();
                    break;
                case 3:
                   // System.out.println("\n\t **** Report the average turnaround time, waiting time, and response time **** ");
                    case3();
                    break;
                case 4:
                    System.out.println("**** Thank you, Goodbye. ****");
                    stop = true;
                    break;
                default:
                    System.out.println("Oops! wrong selection, please retry again.");
                    break;
            }
        }while(!stop);
    }//end main

}//end class
