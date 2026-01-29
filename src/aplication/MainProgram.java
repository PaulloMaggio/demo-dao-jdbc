package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class MainProgram {

    private static Scanner sc = new Scanner(System.in);
    private static SellerDao sellerDao = DaoFactory.createSellerDao();
    private static DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    public static void main(String[] args) {
        int option = -1;

        while (option != 0) {
            System.out.println("\n--- SISTEMA DE GERENCIAMENTO ---");
            System.out.println("1 - Gerenciar Departamentos");
            System.out.println("2 - Gerenciar Vendedores");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    menuDepartment();
                    break;
                case 2:
                    menuSeller();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

    private static void menuDepartment() {
        System.out.println("\n--- MENU DE DEPARTAMENTOS ---");
        System.out.println("1 - Listar todos");
        System.out.println("2 - Buscar por ID");
        System.out.println("3 - Inserir novo");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Deletar");
        System.out.print("Opção: ");
        int opt = sc.nextInt();

        switch (opt) {
            case 1:
                List<Department> list = departmentDao.findAll();
                list.forEach(System.out::println);
                break;
            case 2:
                System.out.print("ID do departamento: ");
                System.out.println(departmentDao.findById(sc.nextInt()));
                break;
            case 3:
                System.out.print("Nome do novo departamento: ");
                sc.nextLine(); // limpar buffer
                departmentDao.insert(new Department(null, sc.nextLine()));
                System.out.println("Inserido com sucesso!");
                break;
            case 4:
                System.out.print("ID para atualizar: ");
                Department dep = departmentDao.findById(sc.nextInt());
                System.out.print("Novo nome: ");
                sc.nextLine();
                dep.setName(sc.nextLine());
                departmentDao.update(dep);
                System.out.println("Atualizado!");
                break;
            case 5:
                System.out.print("ID para deletar: ");
                departmentDao.deleteById(sc.nextInt());
                System.out.println("Deletado!");
                break;
        }
    }

    private static void menuSeller() {
        System.out.println("\n--- MENU DE VENDEDORES ---");
        System.out.println("1 - Listar todos");
        System.out.println("2 - Buscar por ID");
        System.out.println("3 - Inserir novo");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Deletar");
        System.out.print("Opção: ");
        int opt = sc.nextInt();

        switch (opt) {
            case 1:
                List<Seller> list = sellerDao.findAll();
                list.forEach(System.out::println);
                break;
            case 2:
                System.out.print("ID do vendedor: ");
                System.out.println(sellerDao.findById(sc.nextInt()));
                break;
            case 3:
                System.out.print("Nome: "); sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.next();
                System.out.print("Salário Base: ");
                double salary = sc.nextDouble();
                System.out.print("ID do Departamento: ");
                int depId = sc.nextInt();
                
                Seller newSeller = new Seller(null, name, email, new Date(), salary, new Department(depId, null));
                sellerDao.insert(newSeller);
                System.out.println("Inserido! Novo ID: " + newSeller.getId());
                break;
            case 4:
                System.out.print("ID para atualizar: ");
                Seller seller = sellerDao.findById(sc.nextInt());
                System.out.print("Novo Nome: "); sc.nextLine();
                seller.setName(sc.nextLine());
                sellerDao.update(seller);
                System.out.println("Atualizado!");
                break;
            case 5:
                System.out.print("ID para deletar: ");
                sellerDao.deleteById(sc.nextInt());
                System.out.println("Vendedor removido!");
                break;
        }
    }
}