package test.cc.service.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import test.cc.model.Orders;
import test.cc.util.DateTimeUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public class OrdersSpec {

    public static PageRequest buildPageRequest(int pageNum){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return PageRequest.of(pageNum, 10, sort);
    }

    public static Specification<Orders> isSearchKeyLike(String searchKey){
        if (StringUtils.isEmpty(searchKey))
            return null;
        return Specification.where(
                (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("cellphone"), "%" + searchKey + "%")).or(
                (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("userName"), "%" + searchKey + "%"));
    }

    public static Specification<Orders> isIdEqual(Integer id){
        if (id == null)
            return null;
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("id"), id);
    }

    public static Specification<Orders> isStartTimeBetween(Date[] startTime) {
        if (startTime == null || startTime.length != 2)
            return null;
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.between(root.get("startTime"), DateTimeUtil.setBeginTimeInDate(startTime[0]), DateTimeUtil.setEndTimeInDate(startTime[1]));
    }

    public static Specification<Orders> isAssignerEqual(String assigner) {
        if (StringUtils.isEmpty(assigner))
            return null;
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("repair"), assigner);
    }

    public static Specification<Orders> isStateEqual(Integer state){
        if (state == null)
            return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.greaterThan(root.get("state"), -1);
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("state"), state);
    }

    public static Specification<Orders> isTypeIn(List<String> types){
        if (types == null || types.size() == 0)
            return null;
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->{
            CriteriaBuilder.In<String> in = builder.in(root.get("type"));
            types.forEach(type -> in.value(type));
            return in;
        };
    }
}
