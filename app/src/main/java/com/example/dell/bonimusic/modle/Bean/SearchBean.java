package com.example.dell.bonimusic.modle.Bean;

import java.util.List;

/**
 * Created by dell on 2017/10/9.
 * 搜索类
 */
public class SearchBean {

    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"193200","songname":"演员","songid":"242078437","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"薛之谦","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"00081DEFB5AF08599C0D66"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"220","songname":"演员","songid":"74164487","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"千晴绘","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"40","songname":"演员","songid":"65658175","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"蛋堡","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"06081DC3D0D20859C23EDC"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"31","songname":"嘻哈演员","songid":"74231770","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"李萌 (小驴tommy）","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"30","songname":"演员(Actor)","songid":"74196345","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"余佳运","info":"","resource_provider":"1","control":"0100000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"21","songname":"临时演员","songid":"1181694","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"苏有朋","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"84081D679B0A0859B0D8C4"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"11","songname":"初恋杂技演员","songid":"275562640","has_mv":"0","yyr_artist":"0","resource_type_ext":"0","artistname":"Various Artists","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":"0108106cc09009581aa83fL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"10","songname":"演员","songid":"74121195","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"魏佳艺","info":"","resource_provider":"1","control":"0000000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"10","songname":"演员","songid":"74138653","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"麦曲","info":"","resource_provider":"1","control":"0100000000","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","weight":"10","songname":"《演员》高考版搞笑改编","songid":"74143417","has_mv":"0","yyr_artist":"1","resource_type_ext":"0","artistname":"戴凤鑫","info":"","resource_provider":"1","control":"0100000000","encrypted_songid":""}]
     * album : [{"albumname":"模仿演员","weight":"1","artistname":"朱煜","resource_type_ext":"0","artistpic":"http://qukufile2.qianqian.com/data2/pic/0418a75d23106f9b5d16077c07d3f6e3/540189790/540189790.jpg@s_1,w_40,h_40","albumid":"540189790"}]
     * order : artist,song,album
     * error_code : 22000
     * artist : [{"yyr_artist":"0","artistname":"刘航（二人转演员）","artistid":"214649464","artistpic":"http://qukufile2.qianqian.com/data2/pic/246259304/246259304.jpg@s_0,w_48","weight":"21"}]
     */

    private String order;
    private int error_code;
    private List<SongBean> song;
    private List<AlbumBean> album;
    private List<ArtistBean> artist;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public List<ArtistBean> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistBean> artist) {
        this.artist = artist;
    }

    public static class SongBean {
        /**
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * weight : 193200
         * songname : 演员
         * songid : 242078437
         * has_mv : 0
         * yyr_artist : 0
         * resource_type_ext : 0
         * artistname : 薛之谦
         * info :
         * resource_provider : 1
         * control : 0000000000
         * encrypted_songid : 00081DEFB5AF08599C0D66
         */

        private String bitrate_fee;
        private String weight;
        private String songname;
        private String songid;
        private String has_mv;
        private String yyr_artist;
        private String resource_type_ext;
        private String artistname;
        private String info;
        private String resource_provider;
        private String control;
        private String encrypted_songid;

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getResource_provider() {
            return resource_provider;
        }

        public void setResource_provider(String resource_provider) {
            this.resource_provider = resource_provider;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }
    }

    public static class AlbumBean {
        /**
         * albumname : 模仿演员
         * weight : 1
         * artistname : 朱煜
         * resource_type_ext : 0
         * artistpic : http://qukufile2.qianqian.com/data2/pic/0418a75d23106f9b5d16077c07d3f6e3/540189790/540189790.jpg@s_1,w_40,h_40
         * albumid : 540189790
         */

        private String albumname;
        private String weight;
        private String artistname;
        private String resource_type_ext;
        private String artistpic;
        private String albumid;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }
    }

    public static class ArtistBean {
        /**
         * yyr_artist : 0
         * artistname : 刘航（二人转演员）
         * artistid : 214649464
         * artistpic : http://qukufile2.qianqian.com/data2/pic/246259304/246259304.jpg@s_0,w_48
         * weight : 21
         */

        private String yyr_artist;
        private String artistname;
        private String artistid;
        private String artistpic;
        private String weight;

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getArtistid() {
            return artistid;
        }

        public void setArtistid(String artistid) {
            this.artistid = artistid;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
