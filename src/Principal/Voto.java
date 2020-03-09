/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author PC
 */
public class Voto {

    public int getevaluacion() {
        return evaluacion;
    }

    public void setevaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public Voto(int evaluacion, int expdiente, int decision) {
        this.evaluacion = evaluacion;
        this.expediente = expdiente;
        this.decision = decision;
    }

    
  private int evaluacion,  expediente, decision;  
}
