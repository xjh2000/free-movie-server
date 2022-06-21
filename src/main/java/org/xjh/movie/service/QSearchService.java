package org.xjh.movie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xjh.movie.domain.dto.VideoListItem;
import org.xjh.movie.domain.dto.VideoShowDto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xjh
 * @date 6/20/2022 6:30 PM
 */
@ApplicationScoped
public class QSearchService {

    static Pattern VIDEO_PATTERN_1 = Pattern.compile("\"vip_ids\":\\[([^]]*)]");
    static Pattern VIDEO_PATTERN_2 = Pattern.compile("\"listData\":\\[\\[([^]]*)]");

    public VideoShowDto search(String title) {
        // TODO 缓存优化 或者 html解析优化
        VideoShowDto videoInfo = new VideoShowDto();
        videoInfo.title = title;

        // 调用腾讯视频搜索接口
        try {
            Document document = Jsoup.connect("https://v.qq.com/x/search/").data("q", title).get();
            Elements infos = document.getElementsByClass("_infos");

            if (infos.size() <= 0) {
                throw new RuntimeException();
            }

            videoInfo.url = infos.get(0).getElementsByTag("a").get(0).attr("href");
            videoInfo.poster = infos.get(0).getElementsByTag("img").get(0).attr("src");

        } catch (Exception e) {
            throw new NotFoundException("查询失败");
        }

        return videoInfo;
    }

    /**
     * 获取视频列表
     *
     * @param url 腾讯视频url
     * @return 视频列表
     */
    public List<VideoListItem> videoList(String url) {
        try {
            List<VideoListItem> videoList = new ArrayList<>();

            String html = Jsoup.connect(url)
                    .get()
                    .outerHtml();

            Matcher matcher_1 = VIDEO_PATTERN_1.matcher(html);
            Matcher matcher_2 = VIDEO_PATTERN_2.matcher(html);
            String urlPrefix = url.replace(".html", "");
//            解析腾讯视频搜索接口返回的json数据
// 版本一
//            "vip_ids":[
//            {"F":2,"V":"t0042h23a9d"}, // no_vip
//            {"F":7,"V":"t0042h23a9d"}, // vip
//            {"F":4,"V":"s004237dmoq"}  // 预告
//            ]
// 版本二
//                "item_id": "y00435ad386",
//                    "item_type": "1",
//                    "item_source_type": "0",
//                    "item_params": {
//                "union_title": "梦华录_16",
//                        "vid": "y00435ad386",
//                        "imgtag_all": "",
//                        "image_url": "http:\u002F\u002Fpuui.qpic.cn\u002Fvpic_cover\u002Fy00435ad386\u002Fy00435ad386_hz.jpg\u002F496",
//                        "duration": "2794",
//                        "cid": "mzc00200p51jpn7",
//                        "publish_date": "",
//                        "play_title": "梦华录 第16集",
//                        "is_no_store_watch_history": "0",
//                        "refresh_page": "0",
//                        "c_title_output": "16",
//                        "is_trailer": "0",
//                        "title": "16",
//                        "c_full": "0",
//                        "ui_type": "2"
//            },
//                "sub_items": {},
//                "complex_json": ""
//            },

            if (matcher_1.find()) {
                String videoinfo = matcher_1.group(1);
                videoinfo = "[" + videoinfo + "]";
                ObjectMapper objectMapper = new ObjectMapper();
                final int[] episode = {1};
                objectMapper.readTree(videoinfo).forEach(
                        (item) -> {
                            if (item.get("F").asInt() != 4) {
                                VideoListItem videoListItem = new VideoListItem();
                                videoListItem.episode = episode[0];
                                videoListItem.url = urlPrefix + "/" + item.get("V").asText() + ".html";
                                videoList.add(videoListItem);
                                episode[0]++;
                            }
                        }
                );
            } else if (matcher_2.find()) {
                String videoinfo = matcher_2.group(1);
                videoinfo = "[" + videoinfo + "]";
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.readTree(videoinfo).forEach(item -> {
                    int is_trailer = item.get("item_params").get("is_trailer").asInt();
                    if (is_trailer == 0) {
                        VideoListItem videoListItem = new VideoListItem();
                        videoListItem.episode = item.get("item_params").get("title").asInt();
                        videoListItem.url = urlPrefix + "/" + item.get("item_params").get("vid").asText() + ".html";
                        videoList.add(videoListItem);
                    }
                });
            }

            return videoList;
        } catch (IOException e) {
            throw new NotFoundException("查询失败");
        }
    }
}
