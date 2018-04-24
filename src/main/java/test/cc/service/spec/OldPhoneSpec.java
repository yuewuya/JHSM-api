package test.cc.service.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import test.cc.model.OldPhone;
import test.cc.model.Orders;
import test.cc.util.DateTimeUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public class OldPhoneSpec {

    public static PageRequest buildPageRequest(int pageNum){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return PageRequest.of(pageNum, 10, sort);
    }

    public static Specification<OldPhone> isSearchKeyLike(String searchKey){
        if (StringUtils.isEmpty(searchKey))
            return null;
        return Specification.where(
                (Root<OldPhone> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("cellphone"), "%" + searchKey + "%")).or(
                (Root<OldPhone> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("userName"), "%" + searchKey + "%"));
    }

//    public static Specification<Orders> isIdEqual(Integer id){
//        if (id == null)
//            return null;
//        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
//                builder.equal(root.get("id"), id);
//    }

//    public static Specification<Orders> isStartTimeBetween(Date[] startTime) {
//        if (startTime == null || startTime.length != 2)
//            return null;
//        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
//                builder.between(root.get("startTime"), DateTimeUtil.setBeginTimeInDate(startTime[0]), DateTimeUtil.setEndTimeInDate(startTime[1]));
//    }

    public static Specification<OldPhone> isAssignerEqual(String assigner) {
        if (StringUtils.isEmpty(assigner))
            return null;
        return (Root<OldPhone> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("assigner"), assigner);
    }

    public static Specification<OldPhone> isStateEqual(String state) {
        if (StringUtils.isEmpty(state))
            return null;
        return (Root<OldPhone> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("state"), state);
    }

    public static Specification<OldPhone> isTypeEqual(String type) {
        if (StringUtils.isEmpty(type))
            return null;
        return (Root<OldPhone> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.like(root.get("type"), "%"+type+"%");
    }
}
