/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3.classi;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Federico
 */

public class PostFactory {
    //Pattern Design Singleton
    private static PostFactory singleton;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<Post>();

    private PostFactory() {
        
        UtenteFactory utenteFactory = UtenteFactory.getInstance();

        //Creazione Post
        Post post1 = new Post();
        post1.setText("Per me è la cipolla");
        post1.setId(0);
        post1.setUser(utenteFactory.getUtenteById(0));
        post1.setPostType(Post.Type.TEXT);

        Post post2 = new Post();
        post2.setText("Madò che bell stu gatt");
        post2.setContent("img/djanni1.jpg");
        post2.setId(1);
        post2.setUser(utenteFactory.getUtenteById(1));
        post2.setPostType(Post.Type.IMAGE);

        Post post3 = new Post();
        post3.setText("Quanti like merito?");
        post3.setContent("img/djanni2.jpg");
        post3.setId(2);
        post3.setUser(utenteFactory.getUtenteById(1));
        post3.setPostType(Post.Type.IMAGE);

        Post post4 = new Post();
        post4.setText("Passa parola");
        post4.setId(3);
        post4.setUser(utenteFactory.getUtenteById(0));
        post4.setPostType(Post.Type.TEXT);

        Post post5 = new Post();
        post5.setText("ROSIKATE");
        post5.setContent("https://www.google.it/search?q=triplete+inter&rlz=1C1CHZL_itIT679IT679&tbm=isch&imgil=GEoyxK5Ind0YGM%253A%253BxoYtDPzqhM-SrM%253Bhttp%25253A%25252F%25252Fwww.memegen.it%25252Fmemes%25252Ftriplete_inter&source=iu&pf=m&fir=GEoyxK5Ind0YGM%253A%252CxoYtDPzqhM-SrM%252C_&usg=__Ybez3OkdsvPJ0BVonbAXsUnHp4c%3D&biw=1366&bih=662&ved=0ahUKEwjDmoz47tXSAhXLuhoKHQsEC8wQyjcILQ&ei=ntDHWMPTEMv1aouIrOAM#imgrc=GEoyxK5Ind0YGM:");
        post5.setId(4);
        post5.setUser(utenteFactory.getUtenteById(2));
        post5.setPostType(Post.Type.LINK);

        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
        listaPost.add(post5);
    }

    public Post getPostById(int id) {
        for (Post post : this.listaPost) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List getPostList(Utente utente) {

        List<Post> listaPost = new ArrayList<Post>();

        for (Post post : this.listaPost) {
            if (post.getUser().equals(utente)) {
                listaPost.add(post);
            }
        }
        return listaPost;
    }
    public List getPostList(Gruppo group) {

        List<Post> listaPost = new ArrayList<Post>();

        for(Post post : this.listaPost) {
            if (group.controlUtente(post.getUser())) {
                listaPost.add(post);
            }
        }
        return listaPost;
    }  
}
