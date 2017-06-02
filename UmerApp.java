import java.io.*;
import java.util.*;
public class UmerApp {
    private UmerApp () {}
    private static Umer ume;
    private static Menu menumain, menumotoristas, menuclientes, menuregistar, menuregistarUme;
    
    public static void main(String [] args){
    carregarMenus();
    carregarDados();
    imprimeMenuPrincipal();
    try {
      ume.gravar();
    }
    catch(IOException e){
      System.out.println("Não consegui gravar os dados!");
    }
 }

    public static void carregarMenus(){
        String [] principal = {
                               "Iniciar sessão",
                               "Registar Utilizador",
                               "Lista dos 10 clientes que mais gastam",
                               "Lista dos 5 motoristas com mais desvios"
                            };
        String [] motoristas = {
                                "Total faturado por uma viatura",
                                "Total faturado pela empresa",
                                "Lista dos 10 clientes que mais gastam",
                                "Lista dos 5 motoristas com mais desvios",
                                "Lista das viagens realizadas",
                                "Associar viatura",
                                "Criar Viatura nova",
                                "Terminar sessão"
                            };
        String [] clientes = {
                                "Lista dos 10 clientes que mais gastam",
                                "Lista dos 5 motoristas com mais desvios",
                                "Lista das viagens realizadas",
                                "Solicitar uma viagem",
                                "Avaliar o Motorista",
                                "Terminar sessão"
                            };
        String [] registar = {
                                "Registar Cliente",
                                "Registar Motorista"
                            };
        String [] registarUme = {
                                "Carrinha",
                                "Carro",
                                "Moto"
                            };
                            
        menumain = new Menu(principal);
        menumotoristas = new Menu(motoristas);
        menuclientes = new Menu(clientes);
        menuregistar = new Menu(registar);
        menuregistarUme = new Menu(registarUme);
    }
    
    private static void imprimeMenuPrincipal(){
        do{
            menumain.executa();
            switch(menumain.getOpcao()){
                case 1: sessao();
                        break;
                case 2: regiUti();
                        break;
                case 3: listaclientesmaisgastam();
                        break;
                case 4: listamotoristasmaisdesvios();
            }
        } while (menumain.getOpcao()!= 0);
    }
    
        private static void imprimeMenuMotorista(){
        do{
            menumain.executa();
            switch(menumain.getOpcao()){
                case 1: totalfaturadoviatura();
                        break;
                case 2: totalfaturadoimpresa();
                        break;
                case 3: listaclientesmaisgastam();
                        break;
                case 4: listamotoristasmaisdesvios();
                        break;
                case 5: listadasviagensrealizadas();
                        break;
                case 6: associarviatura();
                        break;
                case 7: criarviaturanova();
                        break;
                case 8: ume.terminarsessao();
                        break;
            }
        } while (menumain.getOpcao()!= 0);
    }
    
            private static void imprimeMenuCliente(){
        do{
            menumain.executa();
            switch(menumain.getOpcao()){
                case 1: listaclientesmaisgastam();
                        break;
                case 2: listamotoristasmaisdesvios();
                        break;
                case 3: listadasviagensrealizadas();
                        break;
                case 4: solicitarviagem();
                        break;
                case 5: avaliarcondutor();
                        break;
                case 6: ume.terminarsessao();
                        break;
            }
        } while (menumain.getOpcao()!= 0);
    }
    

   
private static void carregarDados(){
    try {
      ume = Umer.initApp();
    }
    catch (IOException e){
      ume = new Umer();
      System.out.println("Não foi possivel ler os dados!\nErro de leitura!");
    }
    catch (ClassNotFoundException e){
      ume = new Umer();
      System.out.println("Não foi possivel ler os dados!\nFicheiro com formato desconhecido!");
    }
    catch(ClassCastException e){
      ume = new Umer();
      System.out.println("Não foi possivel ler os dados!\nErro de formato!");
    }
  }    
  
    private static void sessao(){
    Scanner input = new Scanner(System.in);
    String email,password;


    System.out.println("Email: ");
    email = input.nextLine();

    System.out.println("Password: ");
    password = input.nextLine();

    try{
      ume.login(email,password);
    }
    catch (SemAutorizacaoException e){
      System.out.println(e.getMessage());
    }
    finally{
      input.close();
    }
    switch(ume.getTipoUtilizador()){
      case 1: imprimeMenuMotorista(); break;
      case 2: imprimeMenuCliente(); break;
    }
  }
  
    private static void regiUti(){
    String email,nome,password,morada,dataDeNascimento;
    Scanner input = new Scanner(System.in);
    Utilizador u = null;
    menuregistar.executa();
    if(menuregistar.getOpcao()==0){
      System.out.println("Registo Cancelado");
      return;
    }
    System.out.println("Insira o email: ");
    email = input.nextLine();

    System.out.println("Insira o nome: ");
    nome = input.nextLine();

    System.out.println("Insira a password: ");
    password = input.nextLine();

    System.out.println("Insira a morada: ");
    morada = input.nextLine();

    System.out.println("Insira a data de nascimento no formato (dd-MM-yyyy): ");
    dataDeNascimento = input.nextLine();

    switch(menuregistar.getOpcao()){
      case 0: input.close(); return;
      case 1: u = new Motorista(); break;
      case 2: u = new Cliente(); break;
    }

    u.setEmail(email);
    u.setNome(nome);
    u.setPassword(password);
    u.setMorada(morada);
    u.setDataDeNascimento(dataDeNascimento);

    try{
      ume.adicionaUti(u); //nome da função de adicionar Utilizador na Umer
    }
    catch (UtilizadorExistenteException e){
      System.out.println(e.getMessage());
    }
    finally{
      input.close();
    }
  }
  //falta acabar
  private static void totalfaturadoviatura (){
      String matricu;
      Scanner input = new Scanner(System.in);
      System.out.println("Insira a matrícula do veículo: ");
      matricu = input.nextLine();
      System.out.println("Indique o perído: ");
      
  }
  
  private static void listaclientesmaisgastam (){
      TreeSet<Cliente> lista = (TreeSet<Cliente>) ume.getFavoritos(); //funçao q escolhe os 10 melhores clientes
      for(Cliente ume : lista){
          System.out.println(ume.getNome());
        }
    }
  
  private static void listamotoristasmaisdesvios (){
      TreeSet<Motorista> lista = (TreeSet<Motorista>) ume.getMaisDesvios(); //funçao q escolhe os 5 piores motoristas
      for(Motorista ume : lista){
          System.out.println(ume.getNome());
        }
    }
    
  private static void listadasviagensrealizadas (){
      Utilizador u = (Utilizador) ume.getUtilizador();
      List<Viagem> lista = (List<Viagem>) ume.getViagensRealizadas(); //funçao q lista as viagens realizadas
      
      for( Viagem v : lista){
          System.out.println(v.toString());
        }
    }
 
  private static void associarviatura (){
      String matricu;
      Motorista m = (Motorista) ume.getUtilizador();
      Scanner input = new Scanner(System.in);
      System.out.println("Indique a matrícula do veículo que quer associar: ");
      matricu = input.nextLine();
      
      try{
          ume.setAssociaViatura(matricu);
        }
      catch(ViaturaInexistenteException|SemAutorizacaoException e){
          System.out.println(e.getMessage());
        }
      finally{
          input.close();
        }
    }
      
      
    
}
                                
                                
                                
                                
                                
                                