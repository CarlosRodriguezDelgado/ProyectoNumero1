public class SingletonPuerto {
    private static SingletonPuerto instancia = null;
    private static final int PUERTO = 5001;

    private SingletonPuerto() {
        
    }

    public  static synchronized SingletonPuerto getInstanceofPuerto(){
        if (instancia==null) {
         return instancia= new SingletonPuerto();
        }
            return instancia;
        }
    

     public int getPuerto() {
        return PUERTO;
    }

}
