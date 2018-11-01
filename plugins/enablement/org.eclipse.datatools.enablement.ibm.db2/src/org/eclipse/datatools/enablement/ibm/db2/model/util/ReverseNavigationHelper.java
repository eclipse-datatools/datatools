/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.model.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <em>ReverseNavigationHelper</em>
 * <p>
 * Helper to augment a one-way EMF relationship with
 * a <i>virtual inverse relationship</i> that makes it possible to navigate the one-way relationship in the opposite direction. 
 * This can be useful in cases where you wish to 
 * define a two-way relationship between classes that come from two different EMF models, but you only have 
 * authority to modify one of the models. In this situation you could create an explicit one-way EMF relationship from
 * the class in your model to a class that belongs to the read-only model, and then you could establish a
 * virtual inverse relationship that would allow you to write code that navigates in the inverse direction.
 * <p>
 * In order to use this helper to define a virtual inverse relationship, a client must do the
 * following:
 * <ol>
 * <li>Create a singleton instance of a {@link InverseAdapter}. This instance has state
 * that identifies the
 * {@link org.eclipse.emf.ecore.EReference} object that defines the one-way 
 * relationship and also 
 * a flag to indicate whether the virtual inverse relationship has a cardinality of 
 * {@link #SINGLE} or {@link #MULTIPLE}.
 * <p>
 * For example, if the EMF package <code>MyPackage</code> has a one-way relationship called <code>foo</code>
 * from class <code>SourceClass</code> to class <code>TargetClass</code>, and you wish the virtual inverse relationship
 * to have a cardinality of <code>SINGLE</code>, you could define this
 * singleton adapter as follows:
 * <p>
 * <xmp>
 *    protected static final EReference fooRef = MyPackage.eINSTANCE.getSourceClass_Foo();
 *    public static final InverseAdapter INVERSE_FOO_ADAPTER = new InverseAdapter(fooRef, SINGLE);
 * </xmp>
 * <p>
 * <li>Make sure that the singleton instance is attached to every potential source object
 * at the time each source object is created.
 * <p>
 * There are different ways that this
 * can be done in EMF. For instance, you can override the 
 * appropriate create method in the generated FactoryImpl for the package.
 * Another way is that an AdapterFactory may be created and registered
 * for objects that require source adapters.
 * <p>
 * <li>Anywhere it is necessary to navigate from the target object back to the source object,
 * use the singleton instance of <code>TargetAdapter</code> created in step 1
 * to invoke the method {@link InverseAdapter#getOppositeEnd(Notifier)}.
 * The argument to this method is the target of the one-way EMF relationship (i.e. it is the <u>source</u> of
 * the virtual inverse relationship.)
 * The value returned by this method is the source of the one-way EMF relationship (i.e. it is the <u>target</u> of
 * the virtual inverse relationship.)
 * <p>
 * You may find it useful to provide a static convenience <i>get</i> method for the virtual inverse relationship.
 * This convenience method could
 * hide the singleton instance of the <code>InverseAdapter</code> and also cast the result of <code>getOppositeEnd</code> 
 * to be the appropriate type. 
 * So, for example, given the one-way relationship called <code>foo</code> described above, 
 * you may wish to define the following convenience method:
 * <p>
 * <xmp>
 *    public static SourceClass getInverseFoo(TargetClass t) {
 *       return (SourceClass) INVERSE_FOO_ADAPTER.getOppositeEnd(t);
 *    }
 * </xmp>
 * <p>
 * Note, if we had chosen to define the virtual inverse relationship to be <code>MULTIPLE</code>
 * instead of <code>SINGLE</code>, the convienience method would be
 * <p>
 * <xmp>
 *    public static EList getInverseFoo(TargetClass t) {
 *       return (EList) INVERSE_FOO_ADAPTER.getOppositeEnd(t);
 *    }
 * </xmp>
 * </ol>
 * <p>
 * <u><i>Usage Notes</i></u>
 * <ul>
 * <li>Note that there is no <code>setOppositeEnd</code> method. 
 * Also note that in the case where the inverse cardinality is
 * <code>MULTIPLE</code>, the EList that is returned from <code>getOppositeEnd</code> in unmodifiable.
 * Thus, the only way to create an instance of a virtual inverse relationship is to create an instance of the corresponeding 
 * one-way EMF relationship.
 * <li>For virtual inverse relationships where the cardinality is <code>SINGLE</code>, if a given target object is already has a
 * source object, the relationship on the old source object will be automatically updated to remove the target.
 * <p>For example, if <code>s1</code> and <code>s2</code> are instances of <code>SourceClass</code> (from the above
 * example)
 * and <code>t1</code> is an instance of <code>TargetClass</code>, 
 * the following code fragment:
 * <p>
 * <xmp>
 *    s1.setFoo(t1); // creates real rel s1->t1 and virtual rel t1->s1
 *    s2.setFoo(t1); // creates real rel s2->t1 and virtual rel t1->s2 and destroys real rel s1->t1
 *    System.out.println(s1.getFoo());
 * </xmp>
 * <p>
 * will print <b>null</b>. 
 * <p>
 * <i>In other words, the fact that the virtual inverse relationship
 * from <code>TargetClass</code> to <code>SourceClass</code> has a cardinality of <code>SINGLE</code>,
 * introduces a side-effect to the generated set method that did not occur when the virtual inverse relationship was
 * not present. </i>
 * <p>
 * <li>If the source and the target of a one-way EMF relationship are serialized
 * into different resources, the link from the source to the target is preserved
 * but the link from the target back to the source is not serialized.
 * In other words, there is no proxy reference from the target resource back to the source resource.
 * (This is how you would expect any one-way EMF relationship to behave. In general, you would not
 * want the addition of a one-way reference to cause the target to be modified in any way.)
 * This means that if the target resource is deserialized but the source resource is not,
 * the virtual inverse relationship will not be created and it will not be possible to navigate
 * from the target object back to the source object.
 * Thus, the source resource must be loaded explicitly in order for
 * the virtual inverse relationship to be navigable.
 * </ul>
 * <p>
 * <u><i>Implementation Notes</i></u>
 * <p>
 * As stated above, the helper requires that a singleton instance of <code>InverseAdapter</code> be attached to
 * every potential source object. This adapter listens for changes to the specified one-way relationship. Whenever
 * a target object is added to this relationship, another adapter called a <code>TargetAdapter</code> is created and
 * attached to the target object. 
 * There is a separate instance of <code>TargetAdapter</code> for each instance of the target object. This adapter has
 * state that includes the source object (or a list of source objects, depending on the inverse cardinality.)
 * This <code>TargetAdapter</code> class is completely hidden from the client. 
 * The implementation of the {@link InverseAdpater#getOppositeEnd} method interogates the 
 * <code>TargetAdapter</code> and returns the object at the other end of the virtual inverse relationship.
 * <p>
 * Whenever the <code>InverseAdapter</code> processes a SET or ADD event that involves a proxy reference, the
 * proxy will automatically be resolved. This will ensure that any new <code>TargetAdapter</code> that needs to 
 * be created will be attached to
 * the resolved target object rather than to the proxy for the target object.
 * <p>
 * The <code>InverseAdapter</code> also listens for changes that remove a target object from the specified one-way relationship.
 * When an UNSET or REMOVE event is processed, the corresponding <code>targetAdapter</code> will be updated as needed.
 */

public class ReverseNavigationHelper {
	
	/**
	 * <code>SINGLE</code> is used when constructing a {@link InverseAdapter} to specify that the cardinality
	 * of the source object is 1.
	 */
	public final static int SINGLE=1;

	/**
	 * <code>MULTIPLE</code> is used when constructing a {@link InverseAdapter} to specify that the cardinality
	 * of the source object is many.
	 */
	public final static int MULTIPLE=-1;

	/**
	 * A <code>InverseAdapter</code> may be attached to any <code>EObject</code> that is the source of 
	 * a one-way EMF relationship. This adapter will provide the ability to
	 * navigate from a target object back to a source object via the {@link ReverseNavigationHelper#getOppositeEnd(Notifier)}
	 * helper method.
	 * <p>
	 * A singleton instance of a InverseAdapter is needed for a given type of relationship.
	 * This singleton instance can be attached to all instances of the 
	 * source of that relationship. The InverseAdapter must be constructed with the
	 * <code>EReference</code> that identifies the relationship and also 
	 * with a flag to indicate whether the virtual inverse relationship has a cardinality of 
	 * {@link #SINGLE} or {@link #MULTIPLE}.
	 * <ul>
	 * <li> If the cardinality is <code>SINGLE</code>, a given target object can have be accessed by only one source object on the
	 * specified relationship.
	 * <li>If the cardinality can is <code>MULTIPLE</code>, a given target object can be accessed by many source objects on
	 * the specified relationship.
	 * <eul>
	 * Note that the cardinality of the virtual inverse relationship is completely independent of the cardinality
	 * of the forward relationship that is specified in the EMF model. Thus this helper class supports 1-to-1,
	 * 1-to-many, many-to-1, and many-to-many relationships.
	 */
	public static class InverseAdapter extends AdapterImpl {
		
		protected EReference reference;
		protected int inverseCardinality;
	
		/**
		 * Constructs a InverseAdapter for the specified relationship. The inverse cardinality will be set to SINGLE.
		 * @param reference The EReference that represents a one-way EMF relationship that needs to be augmented
		 */
		public InverseAdapter(EReference reference) {
			this(reference, SINGLE);
		}
	
		/**
		 * Constructs a InverseAdapter for the specified relationship and inverse cardinality.
		 * @param reference The EReference that represents a one-way EMF relationship that needs to be augmented
		 * @param inverseCardinality Either {@link ReverseNavigationHelper#SINGLE} or {@link ReverseNavigationHelper#MULTIPLE}.
		 * Indicate of whether the target object can be 1 or many source objects via the specified relationship.
		 */
		public InverseAdapter(EReference reference, int inverseCardinality) {
			super();
			// TODO: should we throw and exception if the reference is already bidirectional???
			this.reference = reference;
			this.inverseCardinality = inverseCardinality;
		}
		/**
		 * Returns the source object of an augmented one-way relationship.
		 * <p>
		 * <u><i>Usage Notes</i></u>
		 * <ul>
		 * <li>If the cardinality of the virtual inverse relationship is <code>SINGLE</code>, the <code>getOppositeEnd</code> returns
		 * the single <code>EObject</code> at the other end of the relationship. 
		 * If there is no instance of the virtual inverse relationship
		 * involving the specified object, <b>null</b> is returned.
		 * <li>If the cardinality of the virtual inverse relationship is <code>MULTIPLE</code>, the <code>getOppositeEnd</code> returns
		 * an <code>EList</code> containing all the other end of the relationship.
		 * This <code>EList</code> will be an unmodifiable list. Any attempt to add or remove members to or from the list
		 * will throw an {@link UnsupportedOperationException}.
		 * If there is no instance of the virtual inverse relationship
		 * involving the specified object, an empty list is returned.
		 * </ul>
		 * <p>
		 * 
		 * @param target The object that is the target of the one-way relationship
		 * @return If the inverseCardinality is {@link ReverseNavigationHelper#SINGLE}, the result will be an EObject of the appropriate type.
		 * If the inverseCardinality is {@link ReverseNavigationHelper#MULTIPLE}, the result will be an <b>unmodifiable</b> <code>EList</code> containing the objects.
		 */
		public Object getOppositeEnd(Notifier target) {
			Object source=null;
            
            TargetAdapter a = null;
            for (Iterator iterator = target.eAdapters().iterator(); iterator.hasNext();)
            {
                Adapter adapter = (Adapter)iterator.next();
                if (adapter.isAdapterForType(reference) && adapter instanceof TargetAdapter)
                {
                    a = (TargetAdapter) adapter;
                    break;
                }
            }
            
			if (a==null)
			{
				if (inverseCardinality==SINGLE) 
				{
					a = (TargetAdapter) TargetAdapterFactory.SINGLE_SOURCE.adapt(target,reference);
				}
				else
				{
					a = (TargetAdapter) TargetAdapterFactory.MULTIPLE_SOURCE.adapt(target,reference);
				}
				
			}
			if (a!=null) {
				source = a.getSource();
			}
			return source;
		}	
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
		 */
		public boolean isAdapterForType(Object type) {
			return type==InverseAdapter.class;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			
			if (!reference.equals(notification.getFeature())) return;
			
			int eventType = notification.getEventType();
			
			Object oldTarget = notification.getOldValue(); 
			Object newTarget = notification.getNewValue();
			EObject source = (EObject)notification.getNotifier();
			switch (eventType) {
				case Notification.SET:
					removeSourceFromOldTarget(source, (EObject)oldTarget);
					addNewSourceToNewTarget(notification,(EObject)newTarget,-1);
					break;
				case Notification.UNSET:
					removeSourceFromOldTarget(source,(EObject)oldTarget);
					break;
				case Notification.ADD:
					addNewSourceToNewTarget(notification,(EObject)newTarget, notification.getPosition());
					break;
				case Notification.ADD_MANY:
					Iterator a = ((List)newTarget).iterator();
				    List targetList = (List) newTarget;
					for (int i=0, size=targetList.size(); i<size; i++) {
						addNewSourceToNewTarget(notification,(EObject)targetList.get(i), i);
					}
					break;
				case Notification.REMOVE:
					removeSourceFromOldTarget(source,(EObject)oldTarget);
					break;
				case Notification.REMOVE_MANY:
					Iterator r = ((List)oldTarget).iterator();
					while (r.hasNext()) {
						removeSourceFromOldTarget(source,(EObject)r.next());
					}
					break;
				case Notification.RESOLVE:
					removeSourceFromOldTarget(source, (EObject)oldTarget);
					addNewSourceToNewTarget(notification,(EObject)newTarget,-1);
					break;
			}
		}
		// 	Access and set the TargetAdapter of the appropriate type
		private void addNewSourceToNewTarget(Notification notification, EObject target, int pos) {
			if (target!=null) {
				EObject newSource = (EObject) notification.getNotifier();
				// Target may be a proxy. If so, we should resolve it now
				// so we can add an adapter to the resolved target...
				if (target.eIsProxy()) {
					if (reference.isMany()) {
						List list = (List)newSource.eGet(reference);
						target = (EObject)list.get(pos);
					}
					else
					{
						target = (EObject) newSource.eGet(reference);
					}
				}
				
				// Now get an existing adapter or add a new adapter, as needed. 
				// Chose the type of Adapter based on the cardinality of the inverse rel. 
				TargetAdapter a;
			
				if (inverseCardinality==SINGLE) 
				{
					a = (TargetAdapter) TargetAdapterFactory.SINGLE_SOURCE.adapt(target,reference);
				}
				else
				{
					a = (TargetAdapter) TargetAdapterFactory.MULTIPLE_SOURCE.adapt(target,reference);
				}

				a.setSource(newSource);

			}
		}
		// If this object already has an TargetAdapter for this reference, unset the value
		private void removeSourceFromOldTarget(EObject oldSource, Notifier target) {
			if (target!=null) {
				TargetAdapter a = (TargetAdapter) EcoreUtil.getAdapter(target.eAdapters(),reference);
				if(a!=null)
				a.unsetSource(oldSource);
			}
		}
	} //InverseAdapter
	
	private static class TargetAdapterFactory extends AdapterFactoryImpl {
		
			
			protected static TargetAdapterFactory SINGLE_SOURCE = 
				new TargetAdapterFactory() {
				protected Adapter createAdapter(Notifier target) {
					Adapter a = new TargetWithSingleSourceAdapter();
					a.setTarget(target);
					return a;
				}
				
			};
			protected static TargetAdapterFactory MULTIPLE_SOURCE = 
				new TargetAdapterFactory(){
				protected Adapter createAdapter(Notifier target) {
					Adapter a = new TargetWithMultipleSourceAdapter();
					a.setTarget(target);
					return a;
				}
				
			};
			protected TargetAdapterFactory() {
				super();
			}
			/* (non-Javadoc)
			 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#createAdapter(org.eclipse.emf.common.notify.Notifier, java.lang.Object)
			 */
			protected Adapter createAdapter(Notifier target, Object type) {
				TargetAdapter t = (TargetAdapter)super.createAdapter(target, type);
				t.setReference((EReference)type);
				return t;
			}
			/* (non-Javadoc)
			 * @see org.eclipse.emf.common.notify.AdapterFactory#isFactoryForType(java.lang.Object)
			 */
			public boolean isFactoryForType(Object type) {
				if (type instanceof EObject) {
					EObject eObj = (EObject) type;
					if (eObj.eClass() == EcorePackage.eINSTANCE.getEReference()) return true;
				}
				return super.isFactoryForType(type);
			}
            
            public Adapter adapt(Notifier target, Object type)
            {
                for (Iterator adapters = target.eAdapters().iterator(); adapters.hasNext();)
                {
                    Adapter adapter = (Adapter) adapters.next();
                    if (adapter.isAdapterForType(type) && adapter instanceof TargetAdapter)
                    {
                        return adapter;
                    }
                }
                return adaptNew(target, type);
            }
		}

	private abstract static class TargetAdapter extends AdapterImpl {
		protected EReference reference;
		/**
		 * @param reference The reference to set.
		 */
		public void setReference(EReference reference) {
			this.reference = reference;
		}
		/**
		 * @return Returns the reference.
		 */
		protected EReference getReference() {
			return reference;
		}
		
		protected abstract Object getSource();

		protected abstract void setSource(Object source);
		
		protected abstract void unsetSource(Object source);
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 */
		protected TargetAdapter() {
			super();
		}
	
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
		 */
		public boolean isAdapterForType(Object type) {
			return type == reference;
		}
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			// Do nothing - this adapter is only used to extend the
			// adaptee. It is not used as a listener.
		}
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
		 */
	} //TargetAdapter

	private static class TargetWithSingleSourceAdapter extends TargetAdapter {
		private EObject source=null;
		/* (non-Javadoc)
		 * @see com.ibm.db.models.db2.luw.TableAdapter#setRemoteTable(com.ibm.db.models.db2.luw.RelationalRemoteDataSet)
		 */
		protected void setSource(Object newSource) {
			// For SINGLE inverse cardinality, if there is already a source object
			// for this relationship, we need to modify the old source object to remove the
			// pointer to target
			EObject oldSource = (EObject) getSource();
			if (oldSource!=null && oldSource!=newSource)
			{
				if (reference.isMany())
				{
					EList oldTargetList = (EList) oldSource.eGet(reference);
					oldTargetList.remove(target);
				}
				else
				{
					oldSource.eUnset(reference);
				}
			}
			this.source = (EObject)newSource;
	
		}
		
		protected void unsetSource(Object oldSource)
		{
			source=null;
		}
		/**
		 * @return Returns the source.
		 */
		protected Object getSource() {
			return source;
		}
		

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 */
		public TargetWithSingleSourceAdapter() {
			super();
		}
	} //TargetWithSingleSourceAdapter
	
	private static class TargetWithMultipleSourceAdapter extends TargetAdapter {
		private EList sourceList=null;
		private EList getSourceList()
		{
			if (sourceList==null) 
				{
				sourceList=new BasicEList();
				}
			return sourceList;
		}
		/**
		 * @return Returns the source.
		 */
		public Object getSource() {
			EList list = getSourceList();
			return new UnmodifiableEList(list.size(),list.toArray());
		}

		protected void setSource(Object newSource)
		{
			EList newSourceList = (EList)getSourceList();
			newSourceList.add(newSource);
		}

		protected void unsetSource(Object oldSource)
		{
			getSourceList().remove(oldSource);
		}
		
		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 */
		public TargetWithMultipleSourceAdapter() {
			super();
		}
	} //TargetWithMultipleSourceAdapter
	
	
}
