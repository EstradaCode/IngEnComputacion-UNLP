import java.util.Scanner;
public class ej7{
    public static void main (String[]args){
        int opcion; int atendidos[]= new int[5];
        System.out.println("Que opcion desea realizar? ((0: “cobro de cheque” 1: “depósito/ extracción en cuenta” 2: “pago de impuestos o servicios” 3: “cobro de jubilación” 4: “cobro de planes” )");
        Scanner in = new Scanner(System.in);
        for (int i=0; i<=4;i++){
            atendidos[i]=0;
        }
        opcion= in.nextInt();
        while (opcion != 666){
            switch (opcion){
                case 0: atendidos[0]++;
                        break;
                case 1: atendidos[1]++;
                        break;
                case 2: atendidos[2]++;
                        break;
                case 3: atendidos[3]++;
                        break;
                case 4: atendidos[4]++;
                        break;    
            }
            System.out.println("ATENDIDO. SIGUIENTE!!");
            System.out.println("Que opcion desea realizar? ((0: “cobro de cheque” 1: “depósito/ extracción en cuenta” 2: “pago de impuestos o servicios” 3: “cobro de jubilación” 4: “cobro de planes” )");
            opcion= in.nextInt();
        }
        System.out.println("cantidad de personas atendidas por opciones: ");
        for (int i=0; i<=4;i++){
            System.out.println("para la opcion " + i + "fueron atendidos " + atendidos[i] + "personas");
        }
    }
}
