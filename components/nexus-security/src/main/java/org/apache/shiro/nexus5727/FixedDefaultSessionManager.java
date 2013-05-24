/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2013 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.apache.shiro.nexus5727;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;

/**
 * Fixed {@link DefaultSessionManager} for issue SHIRO-443. This subclass is put into package of Shiro to have
 * shiro-guice's TypeListener applied to it, and result in same behavior as for other Shiro classes.
 * 
 * @author cstamas
 * @see <a href="https://issues.apache.org/jira/browse/SHIRO-443">SHIRO-443 SessionValidationScheduler created multiple
 *      times, enabling it is not thread safe</a>
 * @see <a href="https://issues.sonatype.org/browse/NEXUS-5727>NEXUS-5727 Shiro's session validating thread created
 *      multiple times</a>
 */
public class FixedDefaultSessionManager
    extends DefaultSessionManager
{
    @Override
    protected synchronized void enableSessionValidation()
    {
        final SessionValidationScheduler scheduler = getSessionValidationScheduler();
        if ( scheduler == null )
        {
            super.enableSessionValidation();
        }
    }
}
