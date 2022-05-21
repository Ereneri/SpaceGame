## SpaceGame

☒ Create Panel\
☒ Create Player\
☒ Create Player class\
☒ Make Sprites & Animations\
☒ Create Metal Classses\
☒ Create Metal Sprites\
☐ Create Metal Collision\
☐ Create Pause Screen\
☐ Set Score system\
☐ Create Game Over Screen\
☐ Setup metal and astroid collision\
☐ Create astroid class

# Luke A. - Im perty sure I have fixed that by adding a removeBulletC method in the bullet class. It sets the position of the bullet to (-50, -50)
# which should trigger the if if statement on line 156 in the rocketship class.

# Hey Guys when the bullet hits the astroid it puts this nasty error log into the console.
# I have a feeling this may be the reason for the lag

# Update its line 167 "bulletsClass.bullets.remove(ibull)", if u remove it the errors go away. The issue is somewhere in the remove function.
touch
Exception in thread "AWT-EventQueue-0" java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
        at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
        at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
        at java.base/java.util.Objects.checkIndex(Objects.java:359)
        at java.base/java.util.ArrayList.get(ArrayList.java:427)
        at Main.GamePanel.paintComponent(GamePanel.java:166)
        at java.desktop/javax.swing.JComponent.paint(JComponent.java:1128)
        at java.desktop/javax.swing.JComponent.paintToOffscreen(JComponent.java:5318)
        at java.desktop/javax.swing.RepaintManager$PaintManager.paintDoubleBufferedImpl(RepaintManager.java:1656)
        at java.desktop/javax.swing.RepaintManager$PaintManager.paintDoubleBuffered(RepaintManager.java:1631)
        at java.desktop/javax.swing.RepaintManager$PaintManager.paint(RepaintManager.java:1569)
        at java.desktop/javax.swing.RepaintManager.paint(RepaintManager.java:1336)
        at java.desktop/javax.swing.JComponent._paintImmediately(JComponent.java:5266)
        at java.desktop/javax.swing.JComponent.paintImmediately(JComponent.java:5076)
        at java.desktop/javax.swing.RepaintManager$4.run(RepaintManager.java:878)
        at java.desktop/javax.swing.RepaintManager$4.run(RepaintManager.java:861)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:86)
        at java.desktop/javax.swing.RepaintManager.paintDirtyRegions(RepaintManager.java:861)
        at java.desktop/javax.swing.RepaintManager.paintDirtyRegions(RepaintManager.java:834)
        at java.desktop/javax.swing.RepaintManager.prePaintDirtyRegions(RepaintManager.java:784)
        at java.desktop/javax.swing.RepaintManager$ProcessingRunnable.run(RepaintManager.java:1897)
        at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:773)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:720)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:714)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:86)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:742)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
