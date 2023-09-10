import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

                           /* This is the first class User class which will be mainly used by the user */

class User
{
    public Scanner scanner_obj = new Scanner(System.in);
    public int soldcount[] = new int[] {0,0,0,0,0,0,0,0,0,0};   // the amount of goods sold
    public int snacksstock[] = new int[] {15,15,15,15,15,15,15,15,15,15};   //the amount of snaks avaliable at the moment (can be changed by the authorized)
    public String snacksname[] = new String[] {"Snickers", "Kit Kat", "Hershey", "Orbit", "Lays", "Cheetos", "Coca Cola", "Sprite", "Skittles", "M&M"};
    public int snacksselected[] = new int[11];  // this will store the index of the snaks you will select 
    public int snacksquantity[] = new int[10];  // quantity that user will enter will be stored here
    public int snacksprice[] = new int[] {20,25,50,10,20,45,40,40,50,50};
    
    void items()
    { 
        System.out.println("\t\t  -------------------------------------");
        System.out.println("\t\t |  Select your choice from below"+"     |");
        System.out.println("\t\t  -------------------------------------");
        System.out.println();
        System.out.println("\t\t  -------------------------------------");
        System.out.println("\t\t |  ITEMS AVAILABLE "+"\t  PRICE       |");
        System.out.println("\t\t  -------------------------------------");
        System.out.println("\t\t | 1.Snickers   "+"\t    " + snacksprice[0] +"        |");   
        System.out.println("\t\t | 2.kit kat    "+"\t    " + snacksprice[1] +"        |");     
        System.out.println("\t\t | 3.Hershey    "+"\t    " + snacksprice[2] +"        |");   
        System.out.println("\t\t | 4.Orbit      "+"\t    " + snacksprice[3] +"        |");  
        System.out.println("\t\t | 5.Lays       "+"\t    " + snacksprice[4] +"        |");  
        System.out.println("\t\t | 6.cheetos    "+"\t    " + snacksprice[5] +"        |");  
        System.out.println("\t\t | 7.Coca cola  "+"\t    " + snacksprice[6] +"        |");  
        System.out.println("\t\t | 8.Sprite     "+"\t    " + snacksprice[7] +"        |");  
        System.out.println("\t\t | 9.Skittles   "+"\t    " + snacksprice[8] +"        |");  
        System.out.println("\t\t | 10.M&M       "+"\t    " + snacksprice[9] +"        |");
        System.out.println("\t\t  -------------------------------------");                              
    }

    void select_items()                        /*      Selection of the goods(snacks)      */
    {
        Scanner scanner_obj = new Scanner(System.in);
        int a,i=0,j=0,count=0;
        while(count < 10)
        {
        try
        {
        a = (scanner_obj.nextInt())-1;    // here we are taking input from the user this will act as index 
        }
        catch(Exception e)
        {
            System.out.println("Input type should be integer");
            System.out.println("Please select the goods you want");
            select_items();
            return;
        }
        {
            snacksselected[i] = a;
            if(snacksselected[i]==-1)
                break;
            i++;
            count++;
            if(a > 9 || a < 0)             // if input is in negative or is greater than 10
            {
                System.out.println("Invalid Input");
                i--;
                count--;
            }
            }
            for(j=1;j<i;j++)                    
            {
            if(a == snacksselected[j-1])    // if any input is repeated
            {
                count--;
                i--;
                System.out.println("Repeat");
                break;
            }    
        }
        }
    }
    
    void quantity_selection()
    {
        int a,i,count=0;
        System.out.println("\nEnter the quantity you want :");
        i=0;
        while( (snacksselected[i]!=-1 ) && (count < 10) )
        {
           System.out.print("\n"+snacksname[snacksselected[i]]+" : ");
            try
           {
           a = (scanner_obj.nextInt());
           }
           catch(Exception e)
           {
               System.out.println("Input type should be an integer");
               return;
               //break;
           } 
           if( a >= 0)                         // to confirm the input is not negative
           {
               snacksquantity[i] = a;
               count++;
               i++;
           }
           else 
               System.out.println("Invalid Input");
        }
        i=0;
        while( ( snacksselected[i] != -1 ) && (count < 10) )        
        {
         if(snacksquantity[i]>snacksstock[snacksselected[i]])       // checking the availability of the selected goods
         {
            if(snacksstock[i]==0)
                System.out.println("Out of Stock");   
            else
            {
                System.out.println("\n"+snacksname[snacksselected[i]]+" in stock "+snacksstock[snacksselected[i]]);
                System.out.println("\nWould you like to update your quantity\nYES - 1\nNO - 2");
                try
                {
                a = scanner_obj.nextInt();
                }
                catch(Exception e)
                {
                    System.out.println("Input type should be integer");
                    break;
                    //a = 1;
                }
                if(a==1)
                {
                    System.out.println("\nEnter new quantity");
                    try 
                    {
                    snacksquantity[i] = scanner_obj.nextInt();          // if user want to change the quantity of the 
                    }
                    catch(Exception e)
                    {
                        System.out.println("Input type should be integer");
                        snacksquantity[i] = 0;
                    }
                    if(snacksquantity[i] < 0)                           // selected good(snack) in case of less availability
                    {
                        System.out.println("Invalid Input");
                        snacksquantity[i] = 0;
                    }
                }
                else 
                    snacksquantity[i] = 0;
            }
         }
         count++;
         i++;
        }
    }
    void billing()                  /*        Billing Section        */
    {
        int n,price,total,grandtotal=0,i=0;
        System.out.println("\nYour total bill is :");
        System.out.println("\nItem name\t\tQuantity\tMRP\t\tTotal\n");
        while( (snacksselected[i] != -1) && (i < 10))
        {
            if(snacksquantity[i] > snacksstock[snacksselected[i]])         // in case if someone again tries to input a big number
                snacksquantity[i] = snacksstock[snacksselected[i]];
            n = snacksquantity[i];
            price = snacksprice[snacksselected[i]];
            total = n * price;
            if(total != 0)                        // if the selected item's purchase quantity is zero it will not display
            {    
                if(snacksselected[i] == 0 || snacksselected[i] == 6 || snacksselected[i] == 8)
                    System.out.println(snacksname[snacksselected[i]]+"\t\t    "+snacksquantity[i]+"\t\t"+(snacksprice[snacksselected[i]])+"\t\t"+total+"\n");
                else
                    System.out.println(snacksname[snacksselected[i]]+"\t\t\t    "+snacksquantity[i]+"\t\t"+(snacksprice[snacksselected[i]])+"\t\t"+total+"\n");
                snacksstock[snacksselected[i]] = snacksstock[snacksselected[i]] - snacksquantity[i];
                soldcount[snacksselected[i]] = soldcount[snacksselected[i]] + snacksquantity[i];
            }
            grandtotal += total;
            i++;
        }
        System.out.println("\nGrandTotal = Rs. "+grandtotal+"\n");
    }
}

                /* The Beginning of the Admin class in which the authorised person will be able to update or view the data */

class Admin extends User 
{
    Scanner scanner_obj = new Scanner(System.in);
   void display()
    {
        System.out.println("\t\t  ---------------------------------------------------- ");
        System.out.println("\t\t | \t\t WELCOME TO ADMINSTRATIVE"+"             |");
        System.out.println("\t\t  ---------------------------------------------------- ");
        System.out.println("\t\t | A - View amount of the goods currently is Stock"+"    |");
        System.out.println("\t\t | B - Refil the goods"+"                                |");
        System.out.println("\t\t | C - View the amount of goods sold"+"                  |");
        System.out.println("\t\t | D - Exit"+"                                           |");
        System.out.println("\t\t  ---------------------------------------------------- ");
    }

    void goods_in_stock()
    {
        System.out.println("The amount of goods currently available :");
        int i;
        for(i=0;i<10;i++)
        {
            if(i == 6)
                System.out.println(snacksname[i]+"  "+ "\t =>  "+snacksstock[i]);
            else
                System.out.println(snacksname[i]+"       "+ "\t =>  "+snacksstock[i]);
        }
    }

    void refil()
    {
        int i,j,a;
        System.out.println("Enter 1 if you want to refill all the goods");
        System.out.println("Else enter 2 if you want to refill few selected goods");
        i = scanner_obj.nextInt();
        if(i == 1)
        {
            System.out.println("Enter the amount you want to increase of each snack");
            for(i=0;i<10;)
            {   
                System.out.println(snacksname[i]+" ");
                a = scanner_obj.nextInt();
                if(a >= 0 && a <= 100)
                {
                    snacksstock[i] = snacksstock[i] + a;
                    i++;
                }
                else if(a > 100)
                {
                    System.out.println("Entered value is high please make it below 100");
                    System.out.println("Please re-enter");
                }
                else 
                {
                    System.out.println("Invalid Input");
                    System.out.println("Please re-enter");
                }
            }
        }
        else if(i == 2)
        {
            System.out.println("Select the goods you want to increase from below :\nAnd then enter 0\n");
            items();
            for(i=0;i<10;)
            {
                a = scanner_obj.nextInt()-1;
                if(a >= -1 && a < 10)
                {
                    snacksselected[i] = a;
                    if(a == -1)
                        break;
                    i++;
                    for(j=1;j<i;j++)
                    {
                        if(snacksselected[j-1] == a)
                        {
                            i--;
                            System.out.println("Repeat");
                            break;
                        }
                    }
                }
                else
                    System.out.println("Invalid Input");
            }
                    // goods are selected to be refilled
            System.out.println("Enter the amount you want to increase");
            for(i=0;i<10;)
            {
                System.out.println(snacksname[snacksselected[i]]+" ");
                a = scanner_obj.nextInt();
                if(a >=0 && a <= 100)
                {
                    snacksstock[snacksselected[i]] = snacksstock[snacksselected[i]] + a;
                    i++;
                }
                else if(a > 100)
                    System.out.println("Input quantity large make it less than 100");
                else 
                    System.out.println("Invalid input");
                if(snacksselected[i] == -1)
                    break;
            }

        }
        else 
        {
            System.out.println("Invalid Input");
            System.out.println("Please select a correct option");
            refil();
        }
    }

    void goods_sold()
    {
        System.out.println("The amount of goods sold :");
        int i;
        for(i=0;i<10;i++)
        {
            if(i == 6)
                System.out.println(snacksname[i]+"  "+ "\t =>  "+soldcount[i]);
            else
                System.out.println(snacksname[i]+"       "+ "\t =>  "+soldcount[i]);
        }
    }

}
class Run 
{
    public static void main(String args[])
    {
        String s;
        int n,m,flag = 0;
        Admin admin_obj = new Admin();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter your choice(1-2-3):");
            System.out.print("\n\t\t1.User    \n\t\t2.Admin    \n\t\t3.Exit\n\t\tChoice : ");
            n = sc.nextInt();
            if(n == 3)
                System.exit(0);
            switch (n)
            {
                case 1:
                        while(true)
                        {   
                            admin_obj.items();
                            System.out.println("Select the snacks you want and then enter 0");
                            admin_obj.select_items();
                            //n = admin_obj.quantity_selection();
                            admin_obj.quantity_selection();
                            admin_obj.billing();
                            System.out.println("\n\t\t1 - Continue user menu\n\t\t0 - Exit");
                            m = sc.nextInt();
                            if( m == 0)
                                break;

                        }
                    break;
                case 2:
                        while(true)
                        {
                            Scanner o =new Scanner(System.in);
                            Console c=System.console();
                            if(flag == 0)
                            {
                                String password = "@abhi";
                                System.out.println("Enter the password:");
                                char  cr[]=c.readPassword();
                                s=new String(cr);
                                if(s.equals(password))
                                {
                                    flag = 1;
                                }
                            }
                    
                            if(flag == 1)
                              {
                                   admin_obj.display();
                                   System.out.println("Enter your choice:");
                                   char ch=o.next().charAt(0);
                                   if(ch=='A' || ch=='a')
                                    {
                                        admin_obj.goods_in_stock();                              
                                    } 
                                    else if(ch=='b' || ch=='B') 
                                    {
                                        admin_obj.refil();
                                    }
                                    else if(ch=='c' || ch=='C')
                                    {
                                        admin_obj.goods_sold();    
                                    }
                                    else if(ch=='d' || ch=='D')
                                    {   
                                        flag=0;
                                        break;
                                    }    
                                    else
                                        System.out.println("Invalid Choice");
                                }

                           else     
                            {
                                System.out.println("wrong password.....!!!!");
                                char ch;
                                System.out.println("Press y to Re-Enter the password:");
                                ch=o.next().charAt(0);
                                if(ch=='y' || ch=='Y')
                                {
                                    flag = 0;
                                    continue;
                                }
                                else
                                {
                                    flag = 0;
                                    break;
                                }
                            }   
                      
                    }  
                    break;
        
                default:
                        System.out.println("INVALID CHOICE!");
                        break;
            }   
    
        } 
    }
}