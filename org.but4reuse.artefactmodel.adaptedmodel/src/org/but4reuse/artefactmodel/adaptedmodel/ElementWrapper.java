/**
 */
package org.but4reuse.artefactmodel.adaptedmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.but4reuse.artefactmodel.adaptedmodel.ElementWrapper#getElement <em>Element</em>}</li>
 *   <li>{@link org.but4reuse.artefactmodel.adaptedmodel.ElementWrapper#getBlockElements <em>Block Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getElementWrapper()
 * @model
 * @generated
 */
public interface ElementWrapper extends EObject {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(Object)
	 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getElementWrapper_Element()
	 * @model
	 * @generated
	 */
	Object getElement();

	/**
	 * Sets the value of the '{@link org.but4reuse.artefactmodel.adaptedmodel.ElementWrapper#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(Object value);

	/**
	 * Returns the value of the '<em><b>Block Elements</b></em>' reference list.
	 * The list contents are of type {@link org.but4reuse.artefactmodel.adaptedmodel.BlockElement}.
	 * It is bidirectional and its opposite is '{@link org.but4reuse.artefactmodel.adaptedmodel.BlockElement#getElementWrappers <em>Element Wrappers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block Elements</em>' reference list.
	 * @see org.but4reuse.artefactmodel.adaptedmodel.AdaptedModelPackage#getElementWrapper_BlockElements()
	 * @see org.but4reuse.artefactmodel.adaptedmodel.BlockElement#getElementWrappers
	 * @model opposite="elementWrappers"
	 * @generated
	 */
	EList<BlockElement> getBlockElements();

} // ElementWrapper
