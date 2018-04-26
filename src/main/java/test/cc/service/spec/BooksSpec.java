package test.cc.service.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import test.cc.model.Books;
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
public class BooksSpec {

    public static PageRequest buildPageRequest(int pageNum){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return PageRequest.of(pageNum, 10, sort);
    }

    public static Specification<Books> requiredType(String type){
        if ("维修".equals(type))
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("booktype"), "维修");
        if ("二手回收".equals(type))
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.equal(root.get("booktype"), "二手回收");
        if ("二手出售".equals(type))
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.equal(root.get("booktype"), "二手出售");
        if ("零售".equals(type))
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.equal(root.get("booktype"), "零售");
        if ("支出".equals(type))
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.equal(root.get("booktype"), "支出");
        if ("进货".equals(type))
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.equal(root.get("booktype"), "进货");
        return null;
    }

    public static Specification<Books> isSearchKeyLike(String searchKey){
        if (StringUtils.isEmpty(searchKey))
            return null;
        return Specification.where(
                (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("cellphone"), "%" + searchKey + "%")).or(
                (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("userName"), "%" + searchKey + "%"));
    }

    public static Specification<Books> isIdEqual(Integer id){
        if (id == null)
            return null;
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("id"), id);
    }

    public static Specification<Books> isTimeBetween(Date[] time) {
        if (time == null || time.length != 2)
            return null;
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.between(root.get("time"), DateTimeUtil.setBeginTimeInDate(time[0]), DateTimeUtil.setEndTimeInDate(time[1]));
    }

    public static Specification<Books> isAssignerEqual(String assigner) {
        if (StringUtils.isEmpty(assigner))
            return null;
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("repair"), assigner);
    }

    public static Specification<Books> isStateEqual(Integer state){
        if (state == null)
            return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                    builder.greaterThan(root.get("state"), -1);
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("state"), state);
    }

    public static Specification<Books> isTypeIn(List<String> types){
        if (types == null || types.size() == 0)
            return null;
        return (Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->{
            CriteriaBuilder.In<String> in = builder.in(root.get("type"));
            types.forEach(type -> in.value(type));
            return in;
        };
    }
}
