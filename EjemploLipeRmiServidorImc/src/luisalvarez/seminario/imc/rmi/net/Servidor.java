/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luisalvarez.seminario.imc.rmi.net;

import java.io.IOException;
import luisalvarez.seminario.imc.rmi.lib.IRemotaCalculoImc;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;
/**
 *
 * @author ruisu
 */
public class Servidor {

    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoRmiImcImplem calculoImc;
    private IRemotaCalculoImc calculoImcRemote;

    public Servidor() {
        invocador = new CallHandler();
        servidor = new Server();
        calculoImc = new CalculoRmiImcImplem();
    }

    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaCalculoImc.class, calculoImc);
            servidor.bind(puerto, invocador);
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar métodos remotos");
        } catch (IOException ex) {
            throw new Exception("Error: I/O");
        }
    }

    public void detener() {
        servidor.close();
    }

}
