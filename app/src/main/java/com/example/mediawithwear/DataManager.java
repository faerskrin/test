package com.example.mediawithwear;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<UrlModel> url = new ArrayList<>();
    private List<Content> cont = new ArrayList<>();
    private Context context;

    public DataManager(Context context) {
        this.context = context;
        setUrl();
        setCONTENT();
    }

    public List<Content> getCont() {
        return cont;
    }

    private  void setCONTENT(){

        cont.add(new Content("HUI","Первый"));
        cont.add(new Content("dsa","Второй"));
        cont.add(new Content("czx","Первый"));
        cont.add(new Content("vc","Первый"));
        cont.add(new Content("hkg","Первый"));
        cont.add(new Content("bvc","Третий"));
        cont.add(new Content("iyu","Первый"));
        cont.add(new Content("m,.","Второй"));
        cont.add(new Content("khj","Первый"));
        cont.add(new Content("rty","Первый"));
        cont.add(new Content("3543","Третий"));
        cont.add(new Content("bnv","Первый"));
        cont.add(new Content("xvcx","Первый"));

    }

    private  void  setUrl() {

        url.add(new UrlModel("Девочка", "https://cs7-4v4.vkuseraudio.net/p17/b5ce22d0845334.mp3?extra=iZRajaaZ5h9KdMIHAWhUqM5DOzpMnIzUbhA9ggaR4Xqc9dXXSKoUbENI5IiYvtYDZiTGBjAny3SqG3iA4spjjrkXG6f4a57LrqiBdVV3DG-z9v-1AfFlPsnfE6iTMEJRwvGd6jKnvnlcxDkQVTPiMJbh"));
        url.add(new UrlModel("Мой калашников", "https://cs7-5v4.vkuseraudio.net/p5/c175a729b3353b.mp3?extra=We1ACQdKWrDWGgMua1Zxc8DaXIGYXdmCPxr3Zl4JVCuqj0X8DNFsDspLxwDR3R1f_f6cnc21UxscBvfuz8Owf523leF8LJ1bR0YQFjcsk07U9kf4dWF1X0SAMDIAfKQpQA_rjB6E6tzp_Bc16vl0qkBO"));
        url.add(new UrlModel("Siren", "https://cs7-1v4.vkuseraudio.net/p3/a60304acb79bd3.mp3?extra=jxAqK885PVcstRxHboysiawo8RuqNtRQaQd7Bq1jz3n8dp65c9ahLzrn4kKQd1ygHF7O_gi-zVsTaQ40_-Kz3UZTxdqmio75xRN72bRRzFpMyC5IKYZWWMUSTQyuPgjvjPgt71pB9fgB0uKeRJPcYapa"));

    }

    public List<UrlModel> getUrl() {
        return url;
    }


    public List<Content> getContForGroup(String group) {

        List<Content> contents = new ArrayList<>();
        for (Content content: getCont())
        {
            if (content.getGroup().contains(group))
            {
                contents.add(content);
            }
        }
        return contents;

    }
}
