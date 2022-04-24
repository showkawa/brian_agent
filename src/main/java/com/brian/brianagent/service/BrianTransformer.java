package com.brian.brianagent.service;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

public class BrianTransformer implements ClassFileTransformer {

    public BrianTransformer() {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        list.stream().forEach(vm -> log.info(">>>>>>>>>> VM: {}", vm.displayName()));
    }

    private static final Logger log = LoggerFactory.getLogger(BrianTransformer.class);

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        log.info("====BrianTransformer load class===: classLoader:{}, class:{}, byteSize:{}", loader.getName(), className, classfileBuffer.length);
        return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
    }
}
