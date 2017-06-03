import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class UmerApp  {

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

   private static void carregarMenus(){
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
            menumotoristas.executa();
            switch(menumotoristas.getOpcao()){
                case 1: totalFaturadoViatura();
                        break;
                case 2: listaclientesmaisgastam();
                        break;
                case 3: listaMotoristasMaisDesvios();
                        break;
                case 4: listaDasViagensRealizadas();
                        break;
                case 5: associarviatura();
                        break;
                case 6: criarViaturaNova();
                        break;
                case 7: viagemTerminada();
                        break;
                case 8: ume.terminarSessao();
                        break;
            }
        } while (menumotoristas.getOpcao()!= 0);
    }
    
            private static void imprimeMenuCliente(){
        do{
            menuclientes.executa();
            switch(menuclientes.getOpcao()){
                case 1: listaclientesmaisgastam();
                        break;
                case 2: listaMotoristasMaisDesvios();
                        break;
                case 3: listaDasViagensRealizadas();
                        break;
                case 4: requisitaViagem();
                        break;
                case 5: avaliarCondutor();
                        break;
                case 6: ume.terminarSessao();
                        break;
            }
        } while (menuclientes.getOpcao()!= 0);
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
    catch (PassWordErradaException|UtilizadorInexistenteException|SessaoIniciadaException e){
      System.out.println(e.getMessage());
    }
    finally{
      input.close();
    }
    System.out.println("Login");
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
      case 1: u = new Cliente(); break;
      case 2: u = new Motorista(); break;
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
      
      try{
          double t = ume.fatViatura(inicio,fim,matricu);
          System.out.println("Esta viatura faturou: " + t);
        }
        catch(ViaturaInexistenteException e){
            System.out.println(e.getMessage());
    }
    finally{
        input.close();
    }
  }
  
  private static void listaclientesmaisgastam (){
      ArrayList<Cliente> lista = (ArrayList<Cliente>) ume.top10Gastadores(); //funçao q escolhe os 10 melhores clientes
      for(Cliente ume : lista){
          System.out.println(ume.getNome());
        }
    }
  
  private static void listaMotoristasMaisDesvios (){
      List<Motorista> lista = (List<Motorista>) ume.top5Desviados(); //funçao q escolhe os 5 piores motoristas
      for(Motorista ume : lista){
          System.out.println(ume.getNome());
        }
    }
    
  private static void listaDasViagensRealizadas (){
      Utilizador u = (Utilizador) ume.getUtilizador();
      
      System.out.println("Indique a data inicial: ");
      int ano_inicial = lerInt("Ano: ");
      int mes_inicial = lerInt("Mês: ");
      int dia_inicial = lerInt("Dia: ");
      
      LocalDate di = LocalDate.of(ano_inicial,mes_inicial,dia_inicial);
      
      System.out.println("Indique a data final: ");
      int ano_final = lerInt("Ano: ");
      int mes_final = lerInt("Mês: ");
      int dia_final = lerInt("Dia: ");
      
      LocalDate df = LocalDate.of(ano_inicial,mes_inicial,dia_inicial);
      
      List<Viagem> lista = (List<Viagem>) ume.viagensEntreDatas(di,df);
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
      catch(ViaturaInexistenteException e){
          System.out.println(e.getMessage());
        }
      finally{
          input.close();
        }
    }
      
  private static void viagemTerminada (){
      String matricula;
      Scanner input = new Scanner(System.in);
      System.out.println("Indique a matrícula (XX-YY-ZZ) da viatura com que efectuou a viagem: ");
      matricula = input.nextLine();
      input.close();
   
          ume.finalizaViagem(matricula);
      
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
        ume.criarViaturaNova(viatura);
    }
    catch (ViaturaExistenteException e){
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
  
    private static void requisitaViagem(){
       Scanner input = new Scanner(System.in);
       double xf,yf;
       String matricu;
        
       System.out.println("Insira o seu destino");
       System.out.println("X: ");
       xf = input.nextDouble();
       System.out.println("Y: ");
       yf = input.nextDouble();
       Posicao destino = new Posicao(xf,yf); 
        
       System.out.println("Insira a matrícula do Taxi que prefere: \n(Caso não tenha preferência, insira 1)");
       matricu = input.nextLine();
        

            ume.soliciViagem(matricu,destino);
       
    
            input.close();
       
    }
    
    private static void avaliarCondutor (){
        String email;
        double avaliacao;
        Scanner input = new Scanner(System.in);
        System.out.println("Insira o Email do Motorista que pretende avaliar");
        email = input.nextLine();
        avaliacao = lerDouble("Indique uma classificação de 0 a 100");
        
        try{
        ume.classifMotorista(email,avaliacao);
        }
        catch(UtilizadorInexistenteException e){
          System.out.println(e.getMessage());
        }

        finally{input.close();
        }
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
                                
                                
                                
                                
                                
                                