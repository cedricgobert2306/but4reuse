package org.but4reuse.extension.featureide.ui;

import java.io.File;
import java.net.URI;

import org.but4reuse.adaptedmodel.AdaptedModel;
import org.but4reuse.adaptedmodel.Block;
import org.but4reuse.extension.featureide.fmcreators.impl.FlatFeatureModelCreator;
import org.but4reuse.extension.featureide.utils.FeatureIDEUtils;
import org.but4reuse.utils.files.FileUtils;
import org.but4reuse.utils.ui.dialogs.URISelectionDialog;
import org.but4reuse.visualisation.visualiser.adaptedmodel.BlockElementsMarkupProvider;
import org.but4reuse.visualisation.visualiser.adaptedmodel.BlockElementsOnArtefactsVisualisation;
import org.but4reuse.visualisation.visualiser.adaptedmodel.BlockMarkupKind;
import org.eclipse.contribution.visualiser.core.ProviderDefinition;
import org.eclipse.contribution.visualiser.interfaces.IMarkupKind;
import org.eclipse.contribution.visualiser.views.Menu;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * Create Feature Model action
 * 
 * @author jabier.martinez
 */
public class CreateFeatureModelAction implements IViewActionDelegate {

	Menu menu;

	@Override
	public void run(IAction action) {
		try {
			// Get construction uri from user
			URISelectionDialog inputDialog = new URISelectionDialog(Display.getCurrent().getActiveShell(),
					"Construction URI", "Insert feature model URI", "platform:/resource/projectName/model.xml");
			if (inputDialog.open() != Dialog.OK) {
				return;
			}
			String constructionURI = inputDialog.getValue();
			URI fmURI = null;
			fmURI = new URI(constructionURI);

			File fmFile = FileUtils.getFile(fmURI);
			FileUtils.createFile(fmFile);

			ProviderDefinition definition = BlockElementsOnArtefactsVisualisation.getBlockElementsOnVariantsProvider();
			BlockElementsMarkupProvider markupProvider = (BlockElementsMarkupProvider) definition.getMarkupInstance();

			AdaptedModel adaptedModel = null;
			for (Object o : markupProvider.getAllMarkupKinds()) {
				IMarkupKind kind = (IMarkupKind) o;
				// We don't care about the selection now
				// if (menu.getActive(kind)) {
				Object active = kind;
				if (active instanceof BlockMarkupKind) {
					Block block = ((BlockMarkupKind) active).getBlock();
					adaptedModel = (AdaptedModel) block.eContainer();
					break;
				}
				// }
			}

			// TODO ask the user to select the feature model creator
			FeatureIDEUtils.exportFeatureModel(fmURI, adaptedModel, new FlatFeatureModelCreator());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void init(IViewPart view) {
		menu = (Menu) view;
	}

}