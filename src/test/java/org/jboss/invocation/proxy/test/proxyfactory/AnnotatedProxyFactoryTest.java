/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.invocation.proxy.test.proxyfactory;

import junit.framework.Assert;
import org.jboss.invocation.proxy.ProxyConfiguration;
import org.jboss.invocation.proxy.ProxyFactory;
import org.junit.Test;

import java.lang.reflect.Method;

public class AnnotatedProxyFactoryTest {

    @Test
    public void testSimpleProxy() throws InstantiationException, IllegalAccessException, SecurityException,
            NoSuchMethodException {
        final ProxyConfiguration<AnnotatedClass> proxyConfiguration = new ProxyConfiguration<AnnotatedClass>()
                .setSuperClass(AnnotatedClass.class)
                .setProxyName(getClass().getPackage(),"AnnotatedClassProxy")
                .setClassLoader(AnnotatedClass.class.getClassLoader());
        ProxyFactory<AnnotatedClass> proxyFactory = new ProxyFactory<AnnotatedClass>(proxyConfiguration);
        Assert.assertTrue(proxyFactory.defineClass().isAnnotationPresent(MyAnnotation.class));
        Method method = proxyFactory.defineClass().getDeclaredMethod("method");
        Assert.assertTrue(method.isAnnotationPresent(MyAnnotation.class));
    }

}
