package xyz.cglzwz.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.cglzwz.dto.Exposer;
import xyz.cglzwz.dto.SeckillExcution;
import xyz.cglzwz.dto.SeckillResult;
import xyz.cglzwz.entity.Seckill;
import xyz.cglzwz.enums.SeckillStatusEnum;
import xyz.cglzwz.exception.RepeatKillException;
import xyz.cglzwz.exception.SeckillCloseException;
import xyz.cglzwz.service.SeckillService;

import java.util.Date;
import java.util.List;

/**
 * @author chgl16
 * @date 2019/7/20 10:26
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取秒杀列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        // classpath:/WEB_INF/jsp/list.jsp
        return "list";
    }

    /**
     * 获取秒杀商品详情
     * @param seckillId
     * @param model
     * @return
     */
    @GetMapping("/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            // 重定向回到列表页
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }


    /**
     * Ajax请求秒杀地址
     * @param seckillId
     */
    @PostMapping(value = "/{seckillId}/exposer",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @PostMapping(value = "/{seckillId}/{md5}/excution",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> excute(@PathVariable Long seckillId,
                                                 @PathVariable String md5,
                                                 @CookieValue(value = "userPhone", required = false) String userPhone) {

        if (userPhone == null)
            return new SeckillResult<SeckillExcution>(false, "未注册");
        SeckillResult<SeckillExcution> result;
        try {
            SeckillExcution excution = seckillService.excuteSeckill(seckillId, md5, userPhone);
            result = new SeckillResult<SeckillExcution>(true, excution);
        } catch (RepeatKillException e) {
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatusEnum.REPERT_KILL);
            return new SeckillResult<SeckillExcution>(false, excution);
        } catch (SeckillCloseException e) {
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatusEnum.END);
            return new SeckillResult<SeckillExcution>(false, excution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SeckillExcution excution = new SeckillExcution(seckillId, SeckillStatusEnum.INNER_ERROR);
            return new SeckillResult<SeckillExcution>(false, excution);
        }

        return result;
    }

    /**
     * 获取系统时间
     * @return
     */
    @GetMapping("/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
