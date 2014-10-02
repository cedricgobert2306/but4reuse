/**
 */
package org.but4reuse.artefactmodel.adaptedmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adapted Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.but4reuse.artefactmodel.adaptedmodel.AdaptedModel#getOwnedBlocks <em>Owned Blocks</em>}</li>
 *   <li>{@link org.but4reuse.artefactmodel.adaptedmodel.AdaptedModel#getOwnedAdaptedArtefacts <em>Owned Adapted Artefacts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getAdaptedModel()
 * @model
 * @generated
 */
public interface AdaptedModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Owned Blocks</b></em>' containment reference list.
	 * The list contents are of type {@link org.but4reuse.artefactmodel.adaptedmodel.Block}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Blocks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Blocks</em>' containment reference list.
	 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getAdaptedModel_OwnedBlocks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Block> getOwnedBlocks();

	/**
	 * Returns the value of the '<em><b>Owned Adapted Artefacts</b></em>' containment reference list.
	 * The list contents are of type {@link org.but4reuse.artefactmodel.adaptedmodel.AdaptedArtefact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Adapted Artefacts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Adapted Artefacts</em>' containment reference list.
	 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getAdaptedModel_OwnedAdaptedArtefacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<AdaptedArtefact> getOwnedAdaptedArtefacts();

} // AdaptedModel
