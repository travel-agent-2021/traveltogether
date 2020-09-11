package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.AgencyService;
import com.travelia.service.model.AgencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/agency")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class AgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    /**
     * 获取经销商列表
     * @return 所有经销商model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAllAgencies", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllAgencies() throws BusinessException {
        List<AgencyModel> agencies =  agencyService.getAllAgencies();
        return CommonReturnType.create(agencies, "success");
    }

    /**
     * 根据经销商ID获取用户
     * @return 所有用户model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAgencyById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getAgencyById(@RequestParam(name = "agencyId") Integer agencyId) throws BusinessException {
        AgencyModel agencyModel = agencyService.getAgencyById(agencyId);
        return CommonReturnType.create(agencyModel, "success");
    }

    /**
     * 经销商注册
     * @param agencyAccount
     * @param password
     * @return

    @RequestMapping(value = "/register", method = {RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "password") String password) throws BusinessException {
        if (telephone == null || telephone.equals("")) {
            throw new BusinessException(BusinessError.USER_TELEPHONE_NOT_EMPTY);
        }
        UserModel userModel = new UserModel();
        userModel.setUserTelephone(telephone);
        userModel.setEncryptPassword(password);
        return CommonReturnType.create();
    }*/

    /**
     * 根据经销商账户查找经销商
     * @param agencyAccount
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/agencyAccountSearch", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType searchAgenciesByAgencyAccount(@RequestParam(name = "agencyAccount") String agencyAccount) throws BusinessException {
        List<AgencyModel> agencyModelList = agencyService.getAgenciesLikeAgencyAccount(agencyAccount);
        if (agencyModelList == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "找不到账户信息!");
        }
        return CommonReturnType.create(agencyModelList, "success");
    }


    /**
     * 添加经销商信息
     * @param agencyAccount
     * @param password
     * @param agencyTelephone
     * @param agencyTitle
     * @param agencyEmail
     * @param agencyAddress
     * @param agencyImageSource
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/addAgency", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType addAgency(@RequestParam(name = "agencyAccount") String agencyAccount,
                                    @RequestParam(name = "password") String password,
                                    @RequestParam(name = "agencyTelephone") String agencyTelephone,
                                    @RequestParam(name = "agencyTitle") String agencyTitle,
                                    @RequestParam(name = "agencyEmail") String agencyEmail,
                                    @RequestParam(name = "agencyAddress") String agencyAddress,
                                    @RequestParam(name = "agencyImageSource") String agencyImageSource) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (agencyAccount == null || ("").equals(agencyAccount)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "账户不能为空");
        }
        if (password == null || ("").equals(password)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        }

        AgencyModel agency = new AgencyModel();
        agency.setAgencyAccount(agencyAccount);
        agency.setAgencyTelephone(agencyTelephone);
        agency.setEncryptPassword(encodeByMD5(password));
        agency.setAgencyTitle(agencyTitle);
        agency.setAgencyEmail(agencyEmail);
        agency.setAgencyAddress(agencyAddress);
        agency.setAgencyImageSource(agencyImageSource);
        agencyService.addAgency(agency);


        return CommonReturnType.create();
    }

    @RequestMapping(value = "/updateAgency", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateAgency(@RequestParam(name = "agencyId" ) Integer agencyId,
                                       @RequestParam(name = "agencyAccount") String agencyAccount,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "agencyTelephone") String agencyTelephone,
                                       @RequestParam(name = "agencyTitle") String agencyTitle,
                                       @RequestParam(name = "agencyEmail") String agencyEmail,
                                       @RequestParam(name = "agencyAddress") String agencyAddress,
                                       @RequestParam(name = "agencyImageSource") String agencyImageSource) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (agencyId == null || agencyId.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "经销商ID不能为空");
        }
        if (agencyAccount == null || ("").equals(agencyAccount)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "经销商账户不能为空");
        }
        AgencyModel agencyModel = agencyService.getAgencyById(agencyId);
        if (agencyModel == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "未找到经销商ID");
        }

        if (password == null || ("").equals(password)) {
            agencyModel.setEncryptPassword(agencyModel.getEncryptPassword());
        }

        AgencyModel agency = new AgencyModel();
        agency.setAgencyId(agencyId);
        agency.setAgencyAccount(agencyAccount);
        agency.setAgencyTelephone(agencyTelephone);
        agency.setEncryptPassword(encodeByMD5(password));
        agency.setAgencyTitle(agencyTitle);
        agency.setAgencyEmail(agencyEmail);
        agency.setAgencyAddress(agencyAddress);
        agency.setAgencyImageSource(agencyImageSource);
        agencyService.updateAgency(agency);

        return CommonReturnType.create();
    }

    @RequestMapping(value = "/deleteAgency", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteAgency(@RequestParam(name = "agencyId") Integer agencyId) {
        agencyService.deleteAgency(agencyId);
        return CommonReturnType.create();
    }


    @RequestMapping(value = "/saveImg", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    protected CommonReturnType doGet(HttpServletRequest req, HttpServletResponse resp,@RequestParam(name = "pp") String imgData)
            throws ServletException, IOException {
        return doPost(req,resp,imgData);

    }



    protected CommonReturnType doPost(HttpServletRequest req, HttpServletResponse resp,String imgData)
            throws ServletException, IOException {


        //String imageDataUrl = req.getParameter("pp");

        String imageDataUrl = imgData;

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(imageDataUrl);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        BufferedImage bi1 = ImageIO.read(bais);
        String filename = UUID.randomUUID().toString();
        String filePath="D://traveltogether/travelia/src/main/resources/backstage/image/"+filename+".png";
        //String filePath="D://image/"+filename+".png";
        File w2 = new File(filePath);
        ImageIO.write(bi1, "png", w2);

        return CommonReturnType.create(filePath);
    }
}



