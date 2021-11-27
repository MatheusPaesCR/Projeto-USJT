package src.main;

import src.models.Usuario;

public class LoginInfo {
    private static LoginInfo instancia;
    public Usuario usuarioLogado;

    private LoginInfo() {

    }

    public static LoginInfo getInstancia() {
        if (instancia == null){
            instancia = new LoginInfo();
        }
        return instancia;
    }
}
