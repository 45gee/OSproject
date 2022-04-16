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
   static double AVGturnaround;
   static double AVGwaiting;
   static double AVGresponse;



//////////////////////////////////////////////////////////////////////////
public static void case1() {

System.out.println("Please enter the total number of processes in the system");
  numOfP = input.nextInt();
PCBarray = new PCB[numOfP];
for (int i = 0; i < numOfP; i++) {
PCBarray[i] = new PCB();

PCBarray[i].setProcessID(PNUM++);

System.out.println("Please enter the priority for process #" + (i + 1) + ": ");
int priority;

while (true) {
try {
priority = input.nextInt();
PCBarray[i].setPriority(priority);
break;

} catch (Exception e) {
System.out.println(e.getMessage());
}
}

System.out.println("Please enter the CPU burst time for process #" + (i + 1) + ":");
int cpuBurst = input.nextInt();
PCBarray[i].setCpuBurst(cpuBurst);
PCBarray[i].setICpuBurst(cpuBurst);

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
if(Q1[i] == null)break;
int temp=Q1[i].arrivalTime;
if(maxArrivalQ1<temp)
maxArrivalQ1=temp;}

for(int i=0;i<Q2.length;i++){
if(Q2[i] == null)break;
int temp=Q2[i].arrivalTime;
if(maxArrivalQ2<temp)
maxArrivalQ2=temp;}


}
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
}

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
}

/////////////////////////////////////////////////////////////
public static void case3() {
Queue<PCB> chQ1 = new LinkedList<PCB>();
Queue<PCB> chQ2 = new LinkedList<PCB>();
Queue<PCB> result = new LinkedList<PCB>();

//add elements to chQ1
if(!(Q1.length==0) ){
for(int i=0;i<Q1.length;i++)
chQ1.add(Q1[i]);}

///add elements to chQ2
if(!(Q2.length==0) ){
for(int i=0;i<Q2.length;i++)
chQ2.add(Q2[i]);}

////////////////////////////////////////////////////////case3////////////////////////////////////

//variables needed
int timer =0;
int countP=numOfP;
int Q1ID=-1;
int Q2ID=-1;
int preArrTime=10000000;
int min=10000000;
PCB temp1=new PCB();
PCB temp2=new PCB();
boolean usedQ1=false;
boolean Q1found=false;
boolean intr=false;
String cpuHistory = "";
//int cpuBurst1;

while(true){
if(timer == 20)
break;
if(countP<=0)
break;

//Q1

//find minimum Burst time within timer
if(chQ1.size() >0){

for(int i=0;i<Q1.length;i++){
temp1=chQ1.poll();

if(temp1.getArrivalTime() <= timer){
if(temp1.getCpuBurst() <min){

Q1ID=temp1.getProcessID();
min=temp1.getCpuBurst();
preArrTime=temp1.getArrivalTime();}
if(temp1.getCpuBurst() == min){
if(temp1.getArrivalTime() < preArrTime ){
Q1ID=temp1.getProcessID();
min=temp1.getCpuBurst();
preArrTime=temp1.getArrivalTime();} }
}

//cpuHistory += "P" + Q1ID + "|";

chQ1.add(temp1);
//System.out.println(chQ1.element().getProcessID());
}
//cpu schedule
//cpuHistory += "P" + chQ1.element().getProcessID() + "|";


//find P1 using PID
for(int i=0;i<Q1.length;i++){
temp1=chQ1.poll();

if(Q1ID==temp1.getProcessID()){
Q1found =true;
break;}

else
chQ1.add(temp1);}

//if found assign to P1 proper values
if(Q1found){
temp1.setstartTime(timer);
temp1.setresponseTime(timer-temp1.getArrivalTime());
temp1.setwaitingTime(timer - temp1.getArrivalTime());
temp1.setterminationTime(temp1.getstartTime() + temp1.getCpuBurst() );
temp1.setroundTime( temp1.getterminationTime() - temp1.getArrivalTime() );
timer+=temp1.getCpuBurst()-1;
result.add(temp1);
usedQ1=true;
countP--;
if(!(Q2ID==-1))
intr=true;}

}


//Q2
if((!usedQ1) && !(chQ2.size()==0) ){
if(intr){
chQ2.add(temp2);
Q2ID=-1;
intr=false;}
if(!intr && Q2ID==-1){
temp2=chQ2.poll();
Q2ID=temp2.getProcessID();}


if( temp2.getArrivalTime() <= timer){
if( temp2.getterminationTime() == -1){
temp2.setstartTime(timer);

/////////////////set wait & termination
if(temp2.getstartTime() == 0){
temp2.setresponseTime(0);
temp2.setwaitingTime(0);
}
else if(temp2.getroundTime() - temp2.getICpuBurst() <0){
temp2.setresponseTime(temp2.getArrivalTime()-temp2.getwaitingTime());
temp2.setwaitingTime(temp2.getICpuBurst() - temp2.getroundTime());
}

else{
temp2.setresponseTime(temp2.getwaitingTime()-temp2.getArrivalTime());
temp2.setwaitingTime(temp2.getroundTime() - temp2.getICpuBurst());
}
////////////////////////

temp2.setterminationTime(timer+1);
temp2.setCpuBurst(temp2.getCpuBurst()-1);
//timer++;

}
else{
temp2.setwaitingTime( temp2.getwaitingTime() + (timer - temp2.getterminationTime()) );
temp2.setCpuBurst(temp2.getCpuBurst()-1);
temp2.setterminationTime(timer+1);
//timer++;

}

if( temp2.getCpuBurst() == 0 ){
temp2.setroundTime( temp2.getterminationTime() - temp2.getArrivalTime() );


////////////////////set wait & termination
if(temp2.getstartTime() == 0){
temp2.setresponseTime(0);
temp2.setwaitingTime(0);
}
else if(temp2.getroundTime() - temp2.getICpuBurst() <0){
temp2.setwaitingTime(temp2.getICpuBurst() - temp2.getroundTime());
temp2.setresponseTime(temp2.getwaitingTime());
}
else{
temp2.setwaitingTime(temp2.getroundTime() - temp2.getICpuBurst());
temp2.setresponseTime(temp2.getwaitingTime());
}/////////////////

result.add(temp2);
Q2ID=-1;
countP--;}


}
}//end q2
chQ2.add(temp2);


System.out.println("time: "+timer);
//if(!Q1found && Q2ID==-1)
timer++;
Q1found=false;
usedQ1=false;
Q1ID=-1;
min=10000000;
 }

System.out.printf("%6s | %10s | %12s | %16s | %13s | %s","", "Start time","Waiting time","Termination time",
"Response time","Turn Around time");

for(int i=0;i<numOfP;i++){

try {PCB temp=result.poll();
result.add(temp);
System.out.printf("%n ID: %d | %-10d | %-12d | %-16d | %-13d | %d", temp.getProcessID() , temp.getstartTime(), temp.getwaitingTime(),
temp.getterminationTime(), temp.getresponseTime(), temp.getroundTime());
}catch(Exception IllegalStateException) {System.out.println("oops");}
}

//System.out.print("\n\n CPU Schedule: ["+cpuHistory+"]\n");
PCB current =new PCB();
System.out.print("\n\n[");
for(int j=0; j<numOfP;j++){
current = result.poll();
result.add(current);
for(int i=0 ; i<(current.getterminationTime()-current.getstartTime()) ; i++){
System.out.print("P"+ current.getProcessID()+ "|");
}
}
System.out.print("]");

PCB temp;//edit by r

      for( int i=0;i< numOfP;i++){
         temp=result.poll();
         AVGturnaround=AVGturnaround+temp.getroundTime();
         AVGwaiting=AVGwaiting+temp.getwaitingTime();
         AVGresponse=AVGresponse+temp.getresponseTime();
         result.add(temp);}//end for
      AVGturnaround= AVGturnaround/numOfP;
      AVGwaiting=  AVGwaiting/numOfP;
      AVGresponse=  AVGresponse/numOfP;
System.out.println("\nAvg turn around time:"+AVGturnaround);
System.out.println("Avg waiting time:"+AVGwaiting);
System.out.println("Avg response time:"+AVGresponse);

PCB tmp;
//write in file
try (FileWriter fi = new FileWriter("Report2.txt", true);
BufferedWriter bi = new BufferedWriter(fi);
PrintWriter pi = new PrintWriter(bi);) {

pi.println("Report number -2-");
for(int i=0;i<numOfP;i++){
tmp=result.poll();
result.add(tmp);

try {pi.println("ID: " +tmp.getProcessID()+" | Start time: "+tmp.getstartTime()+" | Waiting time: "+ tmp.getwaitingTime()+" | Termination time: "+ tmp.getterminationTime()+" | Response time: "+ tmp.getresponseTime()+" | Turn around time:"+ tmp.getroundTime());
pi.print("\n\n[");
for(int j=0; j<numOfP;j++){
current = result.poll();
for(int q=0 ; q<(current.getterminationTime()-current.getstartTime()) ; q++){
pi.print("P"+ current.getProcessID()+ "|");}
}
pi.print("]");
}catch(Exception IllegalStateException) {System.out.println("oops file");}
}
}catch(IOException i) {i.printStackTrace();}
}//end case 3

////////////////////////////////////////////////////////////////
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
}catch (IOException i) { i.printStackTrace(); } }
///////////////////////////////////////////////////////////////////
   public static void main(String[] args) throws Exception
    {
        boolean stop = false;
        do
        {
            System.out.println("\n\n\t\t\t **** OS Schedule **** \n");
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
    }

}
//end
