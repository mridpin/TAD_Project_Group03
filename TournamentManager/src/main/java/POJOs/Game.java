package POJOs;
// Generated 17-abr-2018 13:15:53 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Game generated by hbm2java
 */
public class Game  implements java.io.Serializable {


     private Integer gameId;
     private Player playerByLoserId;
     private Player playerByWinnerId;
     private Date date;

    public Game() {
    }

    public Game(Player playerByLoserId, Player playerByWinnerId, Date date) {
       this.playerByLoserId = playerByLoserId;
       this.playerByWinnerId = playerByWinnerId;
       this.date = date;
    }
   
    public Integer getGameId() {
        return this.gameId;
    }
    
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public Player getPlayerByLoserId() {
        return this.playerByLoserId;
    }
    
    public void setPlayerByLoserId(Player playerByLoserId) {
        this.playerByLoserId = playerByLoserId;
    }
    public Player getPlayerByWinnerId() {
        return this.playerByWinnerId;
    }
    
    public void setPlayerByWinnerId(Player playerByWinnerId) {
        this.playerByWinnerId = playerByWinnerId;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}

