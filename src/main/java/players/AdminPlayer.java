package players;

import game.gui.AdminPlayerInterface;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminPlayer extends Player {
    private int amountOfPossibleHints = 3;
    private AdminPlayerInterface adminInterface;

    public AdminPlayer(String nickname) {
        super(nickname);
    }

    public AdminPlayer(String nickname, Socket playerSocket, PrintWriter out, BufferedReader in) {
        super(nickname, playerSocket, out, in);
        adminInterface = new AdminPlayerInterface();
    }

    public AdminPlayer(Player player) {
        super(player.getNickname(), player.getPlayerSocket(), player.getOut(), player.getIn());
        setChatInterface(player.getChatInterface());
        adminInterface = new AdminPlayerInterface();
    }

    public void subtractAmountOfPossibleHints() {
        amountOfPossibleHints--;
    }

    public int getAmountOfPossibleHints() {
        return amountOfPossibleHints;
    }

    public void setAmountOfPossibleHints(int amountOfPossibleHints) {
        this.amountOfPossibleHints = amountOfPossibleHints;
    }

    public AdminPlayerInterface getAdminInterface() {
        return adminInterface;
    }

    public void setAdminInterface(AdminPlayerInterface adminInterface) {
        this.adminInterface = adminInterface;
    }
}
