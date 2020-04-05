/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalbedmanagement;

import java.util.Scanner;
import java.io.*;
///import java.util.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class date implements Serializable
{
    LocalDateTime ld=null;
  //  Date date;
    void getdate()
    {
        ld=LocalDateTime.now();
        System.out.println(ld);

    }
    void printDate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        String formate=ld.format(dtf);
        System.out.println(formate);
    }
    void printDatenow()
    {
         ld=LocalDateTime.now();
        System.out.println(ld);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        System.out.println(dtf.format(ld));
    }
    
    
    
}

/**
 *
 * @author Radadiya
 */
class beds implements Serializable{

    private int p_id, b_id;

    public
                beds()
                {
                    p_id=0;
                    b_id=0;
                }
        void getdata(int pid, int bid) {
        p_id = pid;
        b_id = bid;

    }

    void getpationt(int pid) {
        p_id = pid;
    }

    void getBed(int bid) {
        b_id = bid;
    }

    void showbed() {
        
        System.out.println("bed id " + b_id+" patsient id " + p_id);
        }

    int showpationt() {
       if(p_id > 0)
        {
            //System.out.println("patsient id " + p_id);
        return 1;
        }
       else
       {
           return 0;
       }
    }

    void showBed() {
        System.out.print("\t" + b_id);
    }
}

class hospital implements Serializable{

    private String hname;
    int floors;
    int noofbeds;
    int rooms[] = new int[10];
    int bed[][] = new int[10][10];
    int cpday;
    beds[][][] beds = new beds[10][10][10];

    public
                String hname()
                {
                 return hname;   
                }
                  
                
         void storedata() throws FileNotFoundException, IOException
         {
             FileOutputStream fout = new FileOutputStream("hospital.txt");
ObjectOutputStream oos = new ObjectOutputStream(fout);
oos.writeObject(this);
         }
         
         
        void getdata() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter hospital name");
        hname = scn.nextLine();
        System.out.println("Enter no of floors");
        floors = scn.nextInt();
        for (int i = 0; i < floors; i++) {
            System.out.println("Enter no of room in " + i + " floor");
            rooms[i] = scn.nextInt();
            for (int j = 0; j < rooms[i]; j++) {
                System.out.println("Enter no of bed in " + j + " room");
                bed[i][j] = scn.nextInt();
                for (int k = 0; k < bed[i][j]; k++) {
                    int nobs = ((i + 1) * 100) + ((j + 1) * 10) + k;
               
                    beds[i][j][k] = new beds();
                    beds[i][j][k].getBed(nobs);
                }
            }
        }
        System.out.println("Enter charge of bed ");
        cpday = scn.nextInt();

    }
    
    int bedallocation(int pid)
    {
         Scanner scn = new Scanner(System.in);
        int ni,nj,nk;
        System.out.println("Enter floor no");
        ni=scn.nextInt();
        if(ni<floors && ni>=0)
        {
            System.out.println("enter no of room");
            nj=scn.nextInt();
            if(nj>=0&&nj<rooms[ni])
            {
                System.out.println("ENter no of bed");
                nk=scn.nextInt();
                if(nk>=0&&nk<bed[ni][nj])
                {
                    if(beds[ni][nj][nk].showpationt()==0)
                    {
                    beds[ni][nj][nk].getpationt(pid);
                    beds[ni][nj][nk].showBed();
                    return (((ni + 1) * 100) + ((nj + 1) * 10) + nk);
                    }
                    else
                    {
                        System.out.println("this bed allowcated");
                        bedallocation(pid);
                    }
                }
                else
                {
                    bedallocation(pid);
                }
            }
            else
                {
                    bedallocation(pid);
                }
        }
        else
                {
                    bedallocation(pid);
                }
        return 0;
    }
    void beddeallocation(int bid)
    {
        int f,r,b,n;
            n=bid;
            f=n/100;
            n=n%100;
            r=n/10;
            n=n%10;
            b=n;
            f--;
            r--;
            System.out.println("f "+f+" r "+r+" b "+b);
            beds[f][r][b].showbed();
            beds[f][r][b].getpationt(0);
            beds[f][r][b].showbed();
 
    }
    
    
    void showbeds()
    {
         System.out.println("data");
        for (int i = 0; i < floors; i++) {
            System.out.println("no of room in " + i + " floor  " + rooms[i]);
            for (int j = 0; j < rooms[i]; j++) {
                System.out.println("Enter no of bed in " + j + " room  " + bed[i][j]);
                for (int k = 0; k < bed[i][j]; k++) {
                    beds[i][j][k].showBed();
                }
                System.out.println("\n");
            }
        }
    }
    void showdata() {
        
        System.out.println("hospital name " + hname);
        System.out.println("Enter charge of bed "+cpday);
        
        System.out.println("no of floors " + floors);
        for (int i = 0; i < floors; i++) {
            System.out.println("no of room in " + i + " floor  " + rooms[i]);
            for (int j = 0; j < rooms[i]; j++) {
                System.out.println("bed in " + j + " room  " + bed[i][j]);
                for (int k = 0; k < bed[i][j]; k++) {
                   if(0==beds[i][j][k].showpationt())
                   {
                       beds[i][j][k].showBed();
                   }
                   else
                    {
                       beds[i][j][k].showbed();
                   }
                    
                }
                System.out.println("\n");
            }
        }
    }
    

    }


class pationt implements Serializable{

    private String name;
    String gender;
    String phoneno;
    int age, p_id, ab_id;
    long total_amount=0;
    date admit_date=new date();
    date discharge_date=new date();

    public void getdata(int pid) {
        p_id=pid;
        admit_date.getdate();
        admit_date.printDate();
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter name");
        name = scn.nextLine();
        System.out.println("Enter gender");
        gender = scn.nextLine();
        System.out.println("Enter phoneno");
        phoneno = scn.next("[0-9]{10}");
        System.out.println("Enter age");
        age = scn.nextInt();

    }
    void allowbed(int bed)
    {
        ab_id=bed;
    }
    int getbed()
    {
        return ab_id;
    }
//     void storedata(String file) throws FileNotFoundException, IOException
//         {
//             FileOutputStream fout = new FileOutputStream(file,true);
//ObjectOutputStream oos = new ObjectOutputStream(fout);
//oos.writeObject(this);
//         }
//     
    void showdata() {
        System.out.println("pationt id  " + p_id);
        System.out.println("name" + name);
        System.out.println("gender  " + gender);
        System.out.println("phoneno  " + phoneno);
        System.out.println("age  " + age);
        admit_date.printDate();
        System.out.println("allocated bed  " + ab_id);
        if(total_amount>0)
        {
            System.out.println("total 2"
                    + "amount " + total_amount);
            discharge_date.printDate();
        }

    }
    
    void dischargedate()
    {
        discharge_date.getdate();
    }

    void add(pationt p1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
};

public class Hospitalbedmanagement {

    /**
     * @param args the command line arguments
     */
    
    Scanner scn = new Scanner(System.in);
    hospital h=new hospital(); 
     date d=new date();
    ArrayList<pationt> p=new ArrayList<pationt>();
     pationt p1=new pationt();
    int i=0;
    int j=0;
    private Object pationt;

    public Hospitalbedmanagement() throws IOException {
       
        d.getdate();
        d.printDate();
        this.h = datahospital();
    }
     public void storedata(String file) throws FileNotFoundException, IOException
         {
             FileOutputStream fout = new FileOutputStream(file);
ObjectOutputStream oos = new ObjectOutputStream(fout);
oos.writeObject(p);
oos.close();
         }
    public hospital datahospital() throws IOException
    {       
        File f = new File("hospital.txt");
        if (f.isFile() && f.canRead()) {
        try{     
        FileInputStream fin = null;
            fin = new FileInputStream(f);
            ObjectInputStream ois = null;
              ois = new ObjectInputStream(fin);
                h = (hospital) ois.readObject();
                fin.close();
               h.showdata();
               return h;
        }
        catch (IOException ex) {
           Logger.getLogger(Hospitalbedmanagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hospitalbedmanagement.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        }
        else
        {
             h.getdata();
	    h.storedata();
        }
        return h;
    }
     public void datapationt() throws IOException
    {       
        //ArrayList<pationt> np;
        File f = new File("P.txt");
        if (f.isFile() && f.canRead()) {
           
            try {
                FileInputStream fin = null;
                fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
               p = (ArrayList<pationt>) ois.readObject();
               System.out.println(p);
               ois.close();
               // return np;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Hospitalbedmanagement.class.getName()).log(Level.SEVERE, null, ex);
            }
                
     
                
            
        }
        else
        {
            System.out.println("data not found");
        }
//        return null;
       
    }
     
     public long dat(date d1,date d2)
     {
          long pe = ChronoUnit.DAYS.between(d1.ld,d2.ld);
        
       System.out.println(pe);
       
       return pe;
       //   System.out.println(d.ld.compareTo(d2.ld));
       //   System.out.println(d.ld.compareTo(d2.ld));
     }
     
    public void mainmanu() throws IOException
    {
         h.showdata();
        int choice;
System.out.println("\t\t  "+ h.hname());
d.printDatenow();
System.out.println("*****************MAIN MENU******************************");
System.out.println("\n [1]. Add patient record   ");
System.out.println("\n [2]. View patient details");
System.out.println("\n [3]. Patient discharge");
System.out.println("\n [4]. Display hospital details");
System.out.println("*********************************************************");
System.out.println("\nEnter your choice:");
choice=scn.nextInt();
        switch(choice)
        {
            case 1:
            
               // h.showbeds();
                p1=new pationt();
                p1.getdata(i+1);                
                h.showbeds();
                j=h.bedallocation(i+1);
                System.out.println(j);
                
                p1.allowbed(j);
                
                p.add(p1);
                
                i++;
                
                       
                storedata("P.txt");
                h.storedata();
                returnfunc();
            case 2:
                p.clear();
                        
               datapationt();
                for(j=0;j<p.size();j++)
                {
                   p1=p.get(j);
                   p1.showdata();
                    System.out.println("=============================================");
                }
                returnfunc();
            case 3:
                System.out.println("Enter pationt no");
                j=scn.nextInt();
                long day;
                p1=p.get(j);
                
                p1.dischargedate();
                day=dat(p1.admit_date,p1.discharge_date);
                
                p1.total_amount=h.cpday*day;
                
                p1.showdata();
                
                
                j=p1.getbed();
                System.out.println("total 2"
                    + "amount " + p1.total_amount);
      
                System.out.println("allowcated bed no  "+j);
                h.beddeallocation(j);
                storedata("P.txt");
                  h.storedata();
                returnfunc();
            case 4:
                h.showdata();
                returnfunc();
               }
   }
    
 public void returnfunc() throws IOException
{
int q;
System.out.println("\n Press 1 to return to main menu");
q=scn.nextInt();
if(q==1)
{
mainmanu();
}
else{returnfunc();}

}
 
 
    public static void main(String[] args) throws IOException {
        //new Hospitalbedmanagement();
 new Hospitalbedmanagement().mainmanu();
    }

    

}
