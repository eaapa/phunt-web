package controllers.api.v1;

import java.io.File;

import javax.inject.Inject;

import play.mvc.Http.StatusCode;

import models.Category;
import models.Chain;
import services.ChainService;
import services.LocationService;

public class Chains extends AuthenticatedController {

	@Inject
	private static ChainService chainService;

	public static void heads() {
		renderJSON(chainService.getCategoryHeads(getRequestLatLng()));
	}
	
	public static void create(File image, Category category) {
		if (image == null) {
			badRequest(Chain start image is required!");
		}
		
		Chain chain = chainService.createChain(image, category, getRequestLatLng());
		
		renderJSON(chain);
	}
	
	public static void update(Long chainId, File image) {
		Chain chain = Chain.findById(chainId);
		if (chain == null) {
			notFound("Invalid chain id");
		}
		chainService.updateChainHead(chainId, image, getRequestLatLng());
	}
	
	public static void updateChainHead(File image, Long chainId) {
		chainService.updateChainHead(chainId, image, getRequestLatLng());
	}
}