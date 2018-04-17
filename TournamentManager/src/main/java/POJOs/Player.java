package POJOs;
// Generated 17-abr-2018 13:15:53 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Player generated by hbm2java
 */
public class Player  implements java.io.Serializable {


     private Integer playerId;
     private String name;
     private String password;
     private String email;
     private int points;
     private String nickname;
     private boolean type;
     private Set armies = new HashSet(0);
     private Set gamesForLoserId = new HashSet(0);
     private Set gamesForWinnerId = new HashSet(0);

    public Player() {
    }

	
    public Player(String name, String password, String email, int points, String nickname, boolean type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.points = points;
        this.nickname = nickname;
        this.type = type;
    }
    public Player(String name, String password, String email, int points, String nickname, boolean type, Set armies, Set gamesForLoserId, Set gamesForWinnerId) {
       this.name = name;
       this.password = password;
       this.email = email;
       this.points = points;
       this.nickname = nickname;
       this.type = type;
       this.armies = armies;
       this.gamesForLoserId = gamesForLoserId;
       this.gamesForWinnerId = gamesForWinnerId;
    }
   
    public Integer getPlayerId() {
        return this.playerId;
    }
    
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPoints() {
        return this.points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public boolean isType() {
        return this.type;
    }
    
    public void setType(boolean type) {
        this.type = type;
    }
    public Set getArmies() {
        return this.armies;
    }
    
    public void setArmies(Set armies) {
        this.armies = armies;
    }
    public Set getGamesForLoserId() {
        return this.gamesForLoserId;
    }
    
    public void setGamesForLoserId(Set gamesForLoserId) {
        this.gamesForLoserId = gamesForLoserId;
    }
    public Set getGamesForWinnerId() {
        return this.gamesForWinnerId;
    }
    
    public void setGamesForWinnerId(Set gamesForWinnerId) {
        this.gamesForWinnerId = gamesForWinnerId;
    }




}


