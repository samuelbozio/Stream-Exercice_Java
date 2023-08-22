import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String path = "C:\\Windows\\Temp\\dados.txt";
        Scanner sc = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            List<Funcionario> funcionarioList = new ArrayList<>();

            System.out.println("DADOS: ");
            while(line != null) {

                String fields[] = line.split(",");

                funcionarioList.add(new Funcionario(fields[0],
                        fields[1],
                        Double.parseDouble(fields[2])));

                System.out.println(line);
                line = br.readLine();

            }

            System.out.println();
            System.out.println("SALÁRIO: ");
            double salario = sc.nextDouble();

            Comparator<Funcionario> comparator = Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER);
            List<Funcionario> salarios = funcionarioList.stream()
                    .sorted(comparator)
                    .filter(f -> f.getSalario() > salario)
                    .collect(Collectors.toList());

            System.out.println();
            salarios.forEach(System.out::println);
            System.out.println();

            System.out.println("LETRA INICIAL: ");
            char letra = sc.next().charAt(0);

            double somaSalarios = funcionarioList.stream()
                    .filter(l -> l.getNome().charAt(0) == letra)
                    .mapToDouble(Funcionario::getSalario).
                    sum();

            System.out.println("Letra: " + "'"+letra+"'" + " Soma dos salários: " + String.format("%.2f", somaSalarios));




        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }
}