﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.1
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace VkurseClient.ScheduleChangeService {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", ConfigurationName="ScheduleChangeService.ScheduleChangeService")]
    public interface ScheduleChangeService {
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message getRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.getResponse get(VkurseClient.ScheduleChangeService.getRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message removeRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.removeResponse remove(VkurseClient.ScheduleChangeService.removeRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message insertRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.insertResponse insert(VkurseClient.ScheduleChangeService.insertRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message updateRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.updateResponse update(VkurseClient.ScheduleChangeService.updateRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message getAllRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.getAllResponse getAll(VkurseClient.ScheduleChangeService.getAllRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message findByGroupWeekDayRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.findByGroupWeekDayResponse findByGroupWeekDay(VkurseClient.ScheduleChangeService.findByGroupWeekDayRequest request);
        
        // CODEGEN: Generating message contract since the wrapper namespace (http://DefaultNamespace) of message findByScheduleIDRequest does not match the default value (http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws)
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [System.ServiceModel.XmlSerializerFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc, SupportFaults=true, Use=System.ServiceModel.OperationFormatUse.Encoded)]
        VkurseClient.ScheduleChangeService.findByScheduleIDResponse findByScheduleID(VkurseClient.ScheduleChangeService.findByScheduleIDRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="get", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class getRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public int ID;
        
        public getRequest() {
        }
        
        public getRequest(int ID) {
            this.ID = ID;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class getResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string getReturn;
        
        public getResponse() {
        }
        
        public getResponse(string getReturn) {
            this.getReturn = getReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="remove", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class removeRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public int ID;
        
        public removeRequest() {
        }
        
        public removeRequest(int ID) {
            this.ID = ID;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="removeResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class removeResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public bool removeReturn;
        
        public removeResponse() {
        }
        
        public removeResponse(bool removeReturn) {
            this.removeReturn = removeReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insert", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class insertRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string scheduleChange;
        
        public insertRequest() {
        }
        
        public insertRequest(string scheduleChange) {
            this.scheduleChange = scheduleChange;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insertResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class insertResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public bool insertReturn;
        
        public insertResponse() {
        }
        
        public insertResponse(bool insertReturn) {
            this.insertReturn = insertReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="update", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class updateRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string scheduleChange;
        
        public updateRequest() {
        }
        
        public updateRequest(string scheduleChange) {
            this.scheduleChange = scheduleChange;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="updateResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class updateResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public bool updateReturn;
        
        public updateResponse() {
        }
        
        public updateResponse(bool updateReturn) {
            this.updateReturn = updateReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getAll", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class getAllRequest {
        
        public getAllRequest() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="getAllResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class getAllResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string[] getAllReturn;
        
        public getAllResponse() {
        }
        
        public getAllResponse(string[] getAllReturn) {
            this.getAllReturn = getAllReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByGroupWeekDay", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class findByGroupWeekDayRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public int groupID;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public int week;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=2)]
        public sbyte day;
        
        public findByGroupWeekDayRequest() {
        }
        
        public findByGroupWeekDayRequest(int groupID, int week, sbyte day) {
            this.groupID = groupID;
            this.week = week;
            this.day = day;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByGroupWeekDayResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class findByGroupWeekDayResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string[] findByGroupWeekDayReturn;
        
        public findByGroupWeekDayResponse() {
        }
        
        public findByGroupWeekDayResponse(string[] findByGroupWeekDayReturn) {
            this.findByGroupWeekDayReturn = findByGroupWeekDayReturn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByScheduleID", WrapperNamespace="http://DefaultNamespace", IsWrapped=true)]
    public partial class findByScheduleIDRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public int scheduleID;
        
        public findByScheduleIDRequest() {
        }
        
        public findByScheduleIDRequest(int scheduleID) {
            this.scheduleID = scheduleID;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByScheduleIDResponse", WrapperNamespace="http://nebula.innolab.net.ru:8180/axis/ScheduleChangeService.jws", IsWrapped=true)]
    public partial class findByScheduleIDResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string[] findByScheduleIDReturn;
        
        public findByScheduleIDResponse() {
        }
        
        public findByScheduleIDResponse(string[] findByScheduleIDReturn) {
            this.findByScheduleIDReturn = findByScheduleIDReturn;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ScheduleChangeServiceChannel : VkurseClient.ScheduleChangeService.ScheduleChangeService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ScheduleChangeServiceClient : System.ServiceModel.ClientBase<VkurseClient.ScheduleChangeService.ScheduleChangeService>, VkurseClient.ScheduleChangeService.ScheduleChangeService {
        
        public ScheduleChangeServiceClient() {
        }
        
        public ScheduleChangeServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ScheduleChangeServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ScheduleChangeServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ScheduleChangeServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.getResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.get(VkurseClient.ScheduleChangeService.getRequest request) {
            return base.Channel.get(request);
        }
        
        public string get(int ID) {
            VkurseClient.ScheduleChangeService.getRequest inValue = new VkurseClient.ScheduleChangeService.getRequest();
            inValue.ID = ID;
            VkurseClient.ScheduleChangeService.getResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).get(inValue);
            return retVal.getReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.removeResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.remove(VkurseClient.ScheduleChangeService.removeRequest request) {
            return base.Channel.remove(request);
        }
        
        public bool remove(int ID) {
            VkurseClient.ScheduleChangeService.removeRequest inValue = new VkurseClient.ScheduleChangeService.removeRequest();
            inValue.ID = ID;
            VkurseClient.ScheduleChangeService.removeResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).remove(inValue);
            return retVal.removeReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.insertResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.insert(VkurseClient.ScheduleChangeService.insertRequest request) {
            return base.Channel.insert(request);
        }
        
        public bool insert(string scheduleChange) {
            VkurseClient.ScheduleChangeService.insertRequest inValue = new VkurseClient.ScheduleChangeService.insertRequest();
            inValue.scheduleChange = scheduleChange;
            VkurseClient.ScheduleChangeService.insertResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).insert(inValue);
            return retVal.insertReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.updateResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.update(VkurseClient.ScheduleChangeService.updateRequest request) {
            return base.Channel.update(request);
        }
        
        public bool update(string scheduleChange) {
            VkurseClient.ScheduleChangeService.updateRequest inValue = new VkurseClient.ScheduleChangeService.updateRequest();
            inValue.scheduleChange = scheduleChange;
            VkurseClient.ScheduleChangeService.updateResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).update(inValue);
            return retVal.updateReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.getAllResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.getAll(VkurseClient.ScheduleChangeService.getAllRequest request) {
            return base.Channel.getAll(request);
        }
        
        public string[] getAll() {
            VkurseClient.ScheduleChangeService.getAllRequest inValue = new VkurseClient.ScheduleChangeService.getAllRequest();
            VkurseClient.ScheduleChangeService.getAllResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).getAll(inValue);
            return retVal.getAllReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.findByGroupWeekDayResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.findByGroupWeekDay(VkurseClient.ScheduleChangeService.findByGroupWeekDayRequest request) {
            return base.Channel.findByGroupWeekDay(request);
        }
        
        public string[] findByGroupWeekDay(int groupID, int week, sbyte day) {
            VkurseClient.ScheduleChangeService.findByGroupWeekDayRequest inValue = new VkurseClient.ScheduleChangeService.findByGroupWeekDayRequest();
            inValue.groupID = groupID;
            inValue.week = week;
            inValue.day = day;
            VkurseClient.ScheduleChangeService.findByGroupWeekDayResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).findByGroupWeekDay(inValue);
            return retVal.findByGroupWeekDayReturn;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        VkurseClient.ScheduleChangeService.findByScheduleIDResponse VkurseClient.ScheduleChangeService.ScheduleChangeService.findByScheduleID(VkurseClient.ScheduleChangeService.findByScheduleIDRequest request) {
            return base.Channel.findByScheduleID(request);
        }
        
        public string[] findByScheduleID(int scheduleID) {
            VkurseClient.ScheduleChangeService.findByScheduleIDRequest inValue = new VkurseClient.ScheduleChangeService.findByScheduleIDRequest();
            inValue.scheduleID = scheduleID;
            VkurseClient.ScheduleChangeService.findByScheduleIDResponse retVal = ((VkurseClient.ScheduleChangeService.ScheduleChangeService)(this)).findByScheduleID(inValue);
            return retVal.findByScheduleIDReturn;
        }
    }
}
