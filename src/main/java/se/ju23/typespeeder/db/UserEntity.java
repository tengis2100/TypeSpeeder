package se.ju23.typespeeder.db;

import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "type_speeder", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userid", nullable = false)
    private long userid;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "gamelevel", nullable = false)
    private int gamelevel;
    @Basic
    @Column(name = "gamename", nullable = false, length = 45)
    private String gamename;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamelevel() {
        return gamelevel;
    }

    public void setGamelevel(int gamelevel) {
        this.gamelevel = gamelevel;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userid != that.userid) return false;
        if (gamelevel != that.gamelevel) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (gamename != null ? !gamename.equals(that.gamename) : that.gamename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + gamelevel;
        result = 31 * result + (gamename != null ? gamename.hashCode() : 0);
        return result;
    }

    public static UserEntity createUser(String username, String password, int gamelevel, String gamename) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setGamelevel(gamelevel);
            newUser.setGamename(gamename);

            entityManager.persist(newUser);

            transaction.commit();

            return newUser;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to create user", e);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}

