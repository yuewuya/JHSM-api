package test.cc.service.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import test.cc.model.OldPhone;
import test.cc.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/20.
 */
public class UserSpec {

    public static PageRequest buildPageRequest(int pageNum){
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        return PageRequest.of(pageNum, 10, sort);
    }

    public static Specification<User> isSearchKeyLike(String searchKey){
        if (StringUtils.isEmpty(searchKey))
            return null;
        return Specification.where(
                (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("cellphone"), "%" + searchKey + "%")).or(
                (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                        builder.like(root.get("name"), "%" + searchKey + "%"));
    }

}
