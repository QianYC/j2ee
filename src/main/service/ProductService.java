package service;

import entity.Commodity;
import model.ProductList;
import repository.CommodityRepository;

import java.util.List;

/**
 * @ClassName: ProductService
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/31
 * @Version: 0.0.1
 */
public class ProductService {
    private static ProductService service = new ProductService();
    private CommodityRepository repository = new CommodityRepository();

    private ProductService() {

    }

    public static ProductService getInstance() {
        return service;
    }

    public ProductList getProductList(int page,int size) {
        int maxPage = repository.getPages(size);
        page = page > 0 ? page : 1;
        page = page <= maxPage ? page : maxPage;

        ProductList list = new ProductList(repository.getCommodities(page, size));
        return list;
    }

    public int getMaxPage(int size) {
        return repository.getPages(size);
    }
}
