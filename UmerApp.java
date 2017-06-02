import java.io.*;
import java.util.*;
<<<<<<< HEAD
import java.time.LocalDate;
public class UmerApp {
=======
public class UmerApp  {
>>>>>>> be631f7bd1ffb85dbe2edf7bae1f93556498e702
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
                               "Registar Utilizador"
                            };
        String [] motoristas = {
                                "Total faturado por uma viatura",
                                "Lista dos 10 clientes que mais gastam",
                                "Lista dos 5 motoristas com mais desvios",
                                "Lista das viagens realizadas",
                                "Associar viatura",
                                "Criar Viatura nova",
                                "Viagem Terminada",
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
            }
        } while (menumain.getOpcao()!= 0);
    }
    
        private static void imprimeMenuMotorista(){
        do{
            menumain.executa();
            switch(menumain.getOpcao()){
                case 1: totalFaturadoViatura();
                        break;
<<<<<<< HEAD
                case 2: listaclientesmaisgastam();
                        break;
                case 3: listamotoristasmaisdesvios();
=======
                case 3: listaclientesmaisgastam();
>>>>>>> be631f7bd1ffb85dbe2edf7bae1f93556498e702
                        break;
                case 4: listadasviagensrealizadas();
                        break;
                case 5: associarviatura();
                        break;
                case 6: criarViaturaNova();
                        break;
                case 7: viagemTerminada();
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

  private static void totalFaturadoViatura (){
      String matricu;
      Scanner input = new Scanner(System.in);
      System.out.println("Insira a matrícula do veículo: ");
      matricu = input.nextLine();
      System.out.println("Indique a data inicial: ");
      int ano_inicial = lerInt("Ano: ");
      int mes_inicial = lerInt("Mês: ");
      int dia_inicial = lerInt("Dia: ");
      
      LocalDate inicio = LocalDate.of(ano_inicial,mes_inicial,dia_inicial);
      
      System.out.println("Indique a data final: ");
      int ano_final = lerInt("Ano: ");
      int mes_final = lerInt("Mês: ");
      int dia_final = lerInt("Dia: ");
      
      LocalDate fim = LocalDate.of(ano_final,mes_final,dia_final);
      
      System.out.println(ume.fatViatura(inicio,fim,matricu));
    }
 
  
  private static void listaclientesmaisgastam (){
      TreeSet<Cliente> lista = (TreeSet<Cliente>) ume.top10Gastadores(); //funçao q escolhe os 10 melhores clientes
      for(Cliente ume : lista){
          System.out.println(ume.getNome());
        }
    }
  
  private static void listamotoristasmaisdesvios (){
      TreeSet<Motorista> lista = (TreeSet<Motorista>) ume.maisDesvios(); //funçao q escolhe os 5 piores motoristas
      for(Motorista ume : lista){
          System.out.println(ume.getNome());
        }
    }
    
  private static void listadasviagensrealizadas (){
      Utilizador u = (Utilizador) ume.getUtilizador();
      
      List<Viagem> lista = (List<Viagem>) ume.viagensEntreDatas(d1,d2); //funçao q lista as viagens realizadas
      
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
          ume.associaViatura(m,matricu);
        }
      catch(ViaturaInexistenteException|SemAutorizacaoException e){
          System.out.println(e.getMessage());
        }
      finally{
          input.close();
        }
    }
      
  private static void viagemTerminada (){
    }
  
  private static void criarViaturaNova (){
    Viatura viatura = null;
    menuregistarUme.executa();
    if(menuregistarUme.getOpcao()==0){
        System.out.println("Registo Cancelado!");
        return;
    }
    
    switch(menuregistarUme.getOpcao()){
        case 1: viatura = addCarro(); break;
        case 2: viatura = addCarrinha(); break;
        case 3: viatura = addMoto(); break;
    }
    
    try {
        ume.insereViatura(viatura);
    }
    catch (ViaturaExistenteException|SemAutorizacaoException e){
        System.out.println(e.getMessage());
        criarViaturaNova();
    }
  }
  
  private static Viatura addCarro(){
      Scanner input = new Scanner(System.in);
      String matricu;
      double velocidade, custo, x , y, factor;
      Posicao p;
      Motorista m = (Motorista) ume.getUtilizador();
      
      System.out.println("Insira a matrícula do veículo: ");
      matricu = input.nextLine();
      
      velocidade = lerDouble("Insira a velocidade média do veículo: ");
      
      
      custo = lerDouble("Insira o preço do veículo por kilómetro: ");
     
      
      x = lerLong("Indique a posição em que se encontra (x): ");
      y = lerLong("Indique a posição em que se encontra (y): ");
 
      p = new Posicao(x,y);
      
      return new Moto(velocidade,custo,p,m,matricu,0);
    }
    
    private static Viatura addCarrinha(){
      Scanner input = new Scanner(System.in);
      String matricu;
      double velocidade, custo, x , y, factor;
      Posicao p;
      Motorista m = (Motorista) ume.getUtilizador();
      
      System.out.println("Insira a matrícula do veículo: ");
      matricu = input.nextLine();
      
      velocidade = lerDouble("Insira a velocidade média do veículo: ");
      
      
      custo = lerDouble("Insira o preço do veículo por kilómetro: ");
     
      
      x = lerLong("Indique a posição em que se encontra (x): ");
      y = lerLong("Indique a posição em que se encontra (y): ");
 
      p = new Posicao(x,y);
      
      return new Moto(velocidade,custo,p,m,matricu,0);
    }
    
    private static Viatura addMoto(){
      Scanner input = new Scanner(System.in);
      String matricu;
      double velocidade, custo, x , y, factor;
      Posicao p;
      Motorista m = (Motorista) ume.getUtilizador();
      
      System.out.println("Insira a matrícula do veículo: ");
      matricu = input.nextLine();
      
      velocidade = lerDouble("Insira a velocidade média do veículo: ");
      
      
      custo = lerDouble("Insira o preço do veículo por kilómetro: ");
     
      
      x = lerLong("Indique a posição em que se encontra (x): ");
      y = lerLong("Indique a posição em que se encontra (y): ");
 
      p = new Posicao(x,y);
      
      return new Moto(velocidade,custo,p,m,matricu,0);
    }
    
      private static double lerDouble(String msg){
    Scanner input = new Scanner(System.in);
    double r = 0;
    System.out.println(msg);
    try{
      r= input.nextDouble();
    }
    catch(InputMismatchException e){
      System.out.println("Formato errado!");
      r = lerDouble(msg);
    }
    finally {
      input.close();
    }
    return r;
  }
  
    private static int lerInt(String msg){
    Scanner input = new Scanner(System.in);
    int r = 0;

    System.out.println(msg);
    try{
      r = input.nextInt();
    }
    catch (InputMismatchException e){
      System.out.println("Formato errado!");
      r = lerInt(msg);
    }
    finally{
      input.close();
    }
    return r;
  }
  
    private static long lerLong(String msg){
    Scanner input = new Scanner(System.in);
    long r = 0;

    System.out.println(msg);
    try{
      r = input.nextLong();
    }
    catch (InputMismatchException e){
      System.out.println("Formato errado!");
      r = lerLong(msg);
    }
    finally{
      input.close();
    }
    return r;
  }
}
                                
                                
                                
                                
                                
                                