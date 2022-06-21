package org.xjh.movie.api;

import org.xjh.movie.domain.dto.VideoListItem;
import org.xjh.movie.domain.dto.VideoShowDto;
import org.xjh.movie.service.QSearchService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author xjh
 * @date 6/20/2022 7:30 PM
 */
@Path("/qSearch")
@Produces(MediaType.APPLICATION_JSON)
public class QSearchApi {

    private final QSearchService qSearchService;

    public QSearchApi(QSearchService qSearchService) {
        this.qSearchService = qSearchService;
    }


    /**
     * 在线调用腾讯视频搜索接口
     * <p>待改进：局部类别搜索，接口调用时间的优化</p>
     *
     * @param title 标题
     * @return 视频信息
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VideoShowDto search(@QueryParam("title") String title) {

        return qSearchService.search(title);
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VideoListItem> videoList(@QueryParam("baseUrl") String url) {
        return qSearchService.videoList(url);
    }

}
