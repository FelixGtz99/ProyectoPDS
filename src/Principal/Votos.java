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
public class Votos {

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Votos(int User, int n) {
        this.User = User;
        this.n = n;
    }
  private int User,  n;  
}
