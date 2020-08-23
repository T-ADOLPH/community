package life.majiang.community.dto;

/**
 * @author: T.ADOLPH
 * @date: 2020/8/22 14:42
 * Describe: 保存从github获得的用户信息
 */
public class GithubUser {

    private Long id;
    private String name;
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
