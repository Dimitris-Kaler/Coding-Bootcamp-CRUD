
import crudservices.TrainerCrudServices;
import entities.Trainer;
import java.util.List;
import java.util.Scanner;

public class TrainerTest {


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        Trainer trainer=new Trainer("Sakis","Mpoulas","pastitsio");
        TrainerCrudServices tcs=new TrainerCrudServices();
        tcs.save(trainer);
        tcs.save1(sc);

//
//        List<Trainer> trainerList=tcs.findAll();
//        for(int i=0; i<trainerList.size(); i++){
//            System.out.println(trainerList.get(i));
//        }
//        System.out.println("The entities.Trainer you are looking for is:");
//        System.out.println(tcs.findById(1));
////
//        tcs.delete(11);
//
//        tcs.update(new Trainer(12,"alekos","alekopoulos","podosfairo"));

    }
}
